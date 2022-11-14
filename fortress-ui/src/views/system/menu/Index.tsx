import { defineComponent, onMounted, ref, markRaw, resolveComponent, h } from "vue";
import type { Menu } from "@/types"
import { Column, FormInstance, ElMessageBox, ElMessage } from "element-plus";
import { Delete } from '@element-plus/icons-vue'
import * as MenuApi from '@/api/Menu'
import { useEventPool } from "@/store/index"
import * as icons from "@element-plus/icons-vue"

import CrudToolBar from "@/components/CrudComponents/CrudToolBar"

/**
 * 一个crud业务页面包括：
 * 工具栏：
 *  - 新增：打开弹出层，然后就是弹出层的操作
 *  - 批量删除：获取选中的数据，弹出是否删除提示
 *  - 简单条件搜索：对某个字段进行模糊查找
 * 表格：
 *  - 列字段信息：字段名、字段标识、字段类型、自定义标签样式
 *  - 行操作：对数据行进行操作，标准包含删除和编辑操作
 *  - 权限控制：根据用户角色、角色权限过滤操作上的按钮
 * 分页：
 *  - 是否开启分页：true or false
 *  - 分页接口：数据获取接口
 * 弹出层：
 *  - 各类表单：供用户填写，提交后会对数据进行一定的操作
 *  - 相关提示弹框：点击确定或取消执行相关操作
 * 
 * 提取页面配置数据结构：
 * toolbar: 
 *      buttons, search
 * table:
 *      columns, operations
 * page:
 *      pageable, api,
 * dialog:
 *      editForm, etc..
 * 
 * config: {
 *  toolbar: {
 *      searchable: boolean,
 *      searchPlaceholder: string,
 *      searchIconName: string,
 *      buttons: [{name: string, type: string, onClick: function}]
 *  },
 *  table: {
 *      columns: Column[],
 *      dataApi: {
 *          url: string,
 *          method: string // GET | POST
 *      },
 *      type: string, // tree | page | all
 *  },
 *  dialogs: Form[]
 * }
 * 
 * Column {
 *      identify: string, // 字段标识
 *      label: string, // 表头名
 *      type: string, // 字段类型
 *      render: string, // 渲染器标识
 *      translator: string, // 转换器标识
 *      formType: string, // 表单输入类型
 *      validators: string[], // 校验器标识
 *      visible: boolean, // 是否可见
 *      width: number, // 显示宽度
 *      encrypt: boolean, // 是否需要加密
 *      desensitive: boolean, // 是否需要脱敏
 * }
 * 
 * Form {
 *      name: string,
 *      columns: Column[],
 *      formData: FormData, // 包含了columns中的字段的Map对象
 *      api: {
 *          url: string,
 *          method: string // GET | POST | PUT | DETELE,
 *          data: FormData
 *      }
 * }
 * 
 * 
 */

class MENU_TEMPLATE implements Menu {
    id = ''
    parent = ''
    name = ''
    routeName = ''
    iconName = ''
    pageTitle = ''
    pagePath = ''
    type = 0
    componentPath = ''
    status = 1
    description = ''
    orderNum = 0
    children = []
    hasChildren = false
}

const PAGE_TYPE_MAP = new Map<number, string>([[0, '目录'], [1, '页面'], [2, '按钮'], [3, '链接']])

const STATUS = new Map<number, string>([[1, '正常'], [2, '隐藏']])

export default defineComponent({
    setup() {
        const pageInfo = ref<Menu[]>([])
        const parentSelectorOptions = ref<Menu[]>([])
        const editDialogOpen = ref<boolean>(false)
        const editInfo = ref<Menu>(new MENU_TEMPLATE())
        const editDialogTitle = ref<string>('')
        const formInstance = ref<FormInstance>()
        const eventPool = useEventPool()

        const onCreate = () => {
            editInfo.value = new MENU_TEMPLATE()
            editDialogTitle.value = "新建菜单"
            editDialogOpen.value = true
        }

        const onEdit = (index: number, row: Menu) => {
            editDialogTitle.value = "编辑菜单"
            editDialogOpen.value = true
            editInfo.value = row
        }

        const onCancel = () => {
            editDialogOpen.value = false
            editInfo.value = new MENU_TEMPLATE()
        }

        const onDelete = (index: number, row: Menu) => {
            ElMessageBox.confirm(
                `您将要删除名称为${row.routeName}的菜单，此操作将不可逆转，确定要删除吗？`,
                'Warning',
                {
                    type: 'warning',
                    icon: markRaw(Delete),
                    callback: (action: string) => {
                        if (action === 'confirm') {
                            MenuApi.remove(row.id).then(() => {
                                eventPool.emit({ name: 'flushMenu' })
                                loadAsTree()
                                ElMessage({
                                    message: '删除成功！',
                                    type: 'success',
                                })
                            }).catch(error => {
                                console.log("delete menu occur error: ", error)
                                ElMessage({
                                    message: '删除失败！',
                                    type: 'error',
                                })
                            })
                        }
                    }
                }
            )
        }

        const doCreate = () => {
            MenuApi.add(editInfo.value).then(() => {
                onCancel()
                loadAsTree()
                eventPool.emit({ name: 'flushMenu' })
                ElMessage({
                    message: '添加成功！',
                    type: 'success',
                })
            }).catch(error => {
                console.log("add menu occur error: ", error)
                ElMessage({
                    message: '创建失败！',
                    type: 'error',
                })
            })
        }

        const doUpdate = () => {
            editInfo.value.children = []
            MenuApi.update(editInfo.value).then(() => {
                onCancel()
                loadAsTree()
                eventPool.emit({ name: 'flushMenu' })
                ElMessage({
                    message: '更新成功！',
                    type: 'success',
                })
            }).catch(error => {
                console.log("add menu occur error: ", error)
                ElMessage({
                    message: '更新失败！',
                    type: 'error',
                })
            })
        }

        const doSubmit = () => {
            if (editInfo.value.id) {
                doUpdate()
            } else {
                doCreate()
            }
        }

        const handleSelectionChange = () => {
            console.log()
        }

        const loadAsTree = () => {
            MenuApi.getAsTree().then((result: Menu[]) => {
                pageInfo.value = result
                parentSelectorOptions.value = result
            })
        }

        onMounted(() => {
            loadAsTree()
        })

        const buttons = [{ name: '新增', type: 'primary', onClick: onCreate }, { name: '删除', type: 'danger', onClick: () => console.log("delete") }]
        return () => (
            <>
                <CrudToolBar buttons={buttons}></CrudToolBar>

                <el-table
                    data={pageInfo.value}
                    style={{ width: '100%' }}
                    onSelectionChange={handleSelectionChange}
                    row-key="id"
                    center
                    default-expand-all
                >
                    <el-table-column type="selection" width="55" />
                    <el-table-column label="菜单名称" prop="name" />
                    <el-table-column label="路由名称" prop="routeName" />
                    <el-table-column label="页面标题" prop="pageTitle" />
                    <el-table-column label="页面路径" prop="pagePath" />
                    <el-table-column label="类型" prop="type" v-slots={{
                        default: (scope: Column<Menu>) => (
                            <el-tag>{PAGE_TYPE_MAP.get(scope.row.type)}</el-tag>
                        )
                    }} />
                    <el-table-column label="组件路径" prop="componentPath" />
                    <el-table-column label="状态" prop="status" v-slots={{
                        default: (scope: Column<Menu>) => (
                            <el-tag>{STATUS.get(scope.row.status)}</el-tag>
                        )
                    }} />
                    <el-table-column label="描述" prop="description" />
                    <el-table-column label="序号" prop="orderNum" />
                    <el-table-column label="操作" width="150" v-slots={
                        {
                            default: (scope: Column<Menu>) => (
                                <>
                                    <el-button onClick={() => onEdit(scope.$index, scope.row)}
                                    >编辑</el-button>
                                    <el-button
                                        type="danger"
                                        onClick={() => onDelete(scope.$index, scope.row)}
                                    >删除</el-button>
                                </>
                            )
                        }
                    }>
                    </el-table-column>
                </el-table>

                <el-dialog v-model={editDialogOpen.value} title={editDialogTitle.value} v-slots={{
                    footer: () => (
                        <>
                            <el-button onClick={onCancel}>取消</el-button>
                            <el-button type="primary" onClick={doSubmit}>确定</el-button>
                        </>
                    )
                }}>
                    <el-form model={editInfo.value} ref={formInstance} label-width="80px">
                        <el-form-item label="菜单名称">
                            <el-input v-model={editInfo.value.name} placeholder="请输入菜单名称" />
                        </el-form-item>
                        <el-form-item label="菜单图标">
                            <el-select v-model={editInfo.value.iconName} placeholder="请选择菜单图标">
                                {
                                    Object.getOwnPropertyNames(icons).map((val) => {
                                        if (!val.startsWith("_")) {
                                            return (
                                                <el-option key={val} label={val} value={val}>
                                                    <el-icon> {h(resolveComponent(val))} </el-icon>
                                                    <span>{val}</span>
                                                </el-option>
                                            )
                                        }
                                    })
                                }
                            </el-select>
                        </el-form-item>
                        <el-form-item label="路由名称">
                            <el-input v-model={editInfo.value.routeName} placeholder="请输入路由名称" />
                        </el-form-item>
                        <el-form-item label="页面标题">
                            <el-input v-model={editInfo.value.pageTitle} placeholder="请输入页面标题" />
                        </el-form-item>
                        <el-form-item label="页面路径">
                            <el-input v-model={editInfo.value.pagePath} placeholder="请输入页面路径" />
                        </el-form-item>
                        <el-form-item label="类型">
                            <el-select v-model={editInfo.value.type} placeholder="请选择菜单类型">
                                <el-option label="目录" value={0} />
                                <el-option label="页面" value={1} />
                                <el-option label="按钮" value={2} />
                                <el-option label="链接" value={3} />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="组件路径">
                            <el-input v-model={editInfo.value.componentPath} placeholder="请输入组件路径" />
                        </el-form-item>
                        <el-form-item label="状态">
                            <el-select v-model={editInfo.value.status} placeholder="请选择状态">
                                <el-option label="正常" value={1} />
                                <el-option label="隐藏" value={2} />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="描述">
                            <el-input v-model={editInfo.value.description} placeholder="请输入描述" />
                        </el-form-item>
                        <el-form-item label="父级菜单">
                            <el-tree-select
                                v-model={editInfo.value.parent}
                                data={parentSelectorOptions.value}
                                node-key="id"
                                default-expand-all
                                render-after-expand={false}
                                default-checked-keys={[]}
                                show-checkbox
                                props={{
                                    label: 'name'
                                }}
                            />
                        </el-form-item>
                        <el-form-item label="排序">
                            <el-input v-model={editInfo.value.orderNum} type="number" placeholder="请输入序号" />
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </>
        )
    }
})