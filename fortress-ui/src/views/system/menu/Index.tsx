import { defineComponent, onMounted, ref, markRaw } from "vue";
import type { Menu } from "@/types"
import { Column, FormInstance, ElMessageBox, ElMessage } from "element-plus";
import { Delete } from '@element-plus/icons-vue'
import * as MenuApi from '@/api/Menu'

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
        const searchKey = ref<string>('')
        const editDialogOpen = ref<boolean>(false)
        const editInfo = ref<Menu>(new MENU_TEMPLATE())
        const editDialogTitle = ref<string>('')
        const formInstance = ref<FormInstance>()

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
            MenuApi.update(editInfo.value).then(() => {
                onCancel()
                loadAsTree()
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

        return () => (
            <>
                <el-row style={{ marginBottom: '10px' }} justify="space-between">
                    <el-col span={4}>
                        <el-button-group>
                            <el-button type="primary" onClick={onCreate}>新增</el-button>
                            <el-button type="danger">删除</el-button>
                        </el-button-group>
                    </el-col>
                    <el-col span={4}>
                        <el-input v-model={searchKey.value} placeholder="请输入搜索条件" clearable v-slots={{
                            append: () => (
                                <el-button icon="Search" />
                            )
                        }} />
                    </el-col>
                </el-row>
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
                            <el-tab>{PAGE_TYPE_MAP.get(scope.row.type)}</el-tab>
                        )
                    }} />
                    <el-table-column label="组件路径" prop="componentPath" />
                    <el-table-column label="状态" prop="status" v-slots={{
                        default: (scope: Column<Menu>) => (
                            <el-tab>{STATUS.get(scope.row.status)}</el-tab>
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