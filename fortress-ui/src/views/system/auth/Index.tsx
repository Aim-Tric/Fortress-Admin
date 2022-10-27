import { defineComponent, onMounted, ref, markRaw } from "vue";
import type { Page, Auth } from "@/types"
import { Column, FormInstance, ElMessageBox, ElMessage } from "element-plus";
import { Delete } from '@element-plus/icons-vue'
import * as AuthApi from '@/api/Auth'

class AUTH_TEMPLATE implements Auth {
    id = ''
    parent = ''
    name = ''
    identify = ''
    status = 0
    orderNum = 0
}

export default defineComponent({
    setup() {
        const pageInfo = ref<Page<Auth>>({
            records: [],
            current: 1,
            size: 10,
            total: 0
        })
        const searchKey = ref<string>('')
        const editDialogOpen = ref<boolean>(false)
        const editInfo = ref<Auth>(new AUTH_TEMPLATE())
        const editDialogTitle = ref<string>('')
        const formInstance = ref<FormInstance>()

        const onCreate = () => {
            editDialogTitle.value = "新建权限"
            editDialogOpen.value = true
        }

        const onEdit = (index: number, row: Auth) => {
            editDialogTitle.value = "编辑权限"
            editDialogOpen.value = true
            editInfo.value = row
        }

        const onCancel = () => {
            editDialogOpen.value = false
            editInfo.value = new AUTH_TEMPLATE()
        }

        const onDelete = (index: number, row: Auth) => {
            ElMessageBox.confirm(
                `您将要删除名称为${row.name}的权限，此操作将不可逆转，确定要删除吗？`,
                'Warning',
                {
                    type: 'warning',
                    icon: markRaw(Delete),
                    callback: (action: string) => {
                        if (action === 'confirm') {
                            AuthApi.remove(row.id).then(() => {
                                loadByPage()
                                ElMessage({
                                    message: '删除成功！',
                                    type: 'success',
                                })
                            }).catch(error => {
                                console.log("delete user occur error: ", error)
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
            AuthApi.add(editInfo.value).then(() => {
                onCancel()
                loadByPage()
                ElMessage({
                    message: '添加成功！',
                    type: 'success',
                })
            }).catch(error => {
                console.log("add user occur error: ", error)
                ElMessage({
                    message: '创建失败！',
                    type: 'error',
                })
            })
        }

        const handleSelectionChange = () => {
            console.log()
        }

        const loadByPage = () => {
            AuthApi.page(pageInfo.value.current, pageInfo.value.size).then((result: Page<Auth>) => {
                pageInfo.value = result
            })
        }

        onMounted(() => {
            loadByPage()
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
                    data={pageInfo.value.records}
                    style="width: 100%"
                    onSelectionChange={handleSelectionChange}
                    tree-props={{ children: 'children', hasChildren: 'hasChildren' }}
                >
                    <el-table-column type="selection" width="55" />
                    <el-table-column label="权限名称" width="180" property="name" />
                    <el-table-column label="权限标识" width="180" property="identify" />
                    <el-table-column label="序号" width="180" property="orderNum" />
                    <el-table-column label="操作" v-slots={
                        {
                            default: (scope: Column<Auth>) => (
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

                <el-pagination
                    layout="total, sizes, prev, pager, next, jumper"
                    total={pageInfo.value.total}
                    v-models={[[pageInfo.value.size, 'page-size'], [pageInfo.value.current, 'current-page']]}
                    onSizeChange={loadByPage}
                    onCurrentChange={loadByPage} />

                <el-dialog v-model={editDialogOpen.value} title={editDialogTitle.value} v-slots={{
                    footer: () => (
                        <>
                            <el-button onClick={onCancel}>取消</el-button>
                            <el-button type="primary" onClick={doCreate}>确定</el-button>
                        </>
                    )
                }}>
                    <el-form model={editInfo.value} ref={formInstance} label-width="80px">
                        <el-form-item label="权限名称">
                            <el-input v-model={editInfo.value.name} placeholder="请输入权限名称" />
                        </el-form-item>
                        <el-form-item label="权限标识">
                            <el-input v-model={editInfo.value.identify} placeholder="请输入权限标识" />
                        </el-form-item>
                        <el-form-item label="父级权限">

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