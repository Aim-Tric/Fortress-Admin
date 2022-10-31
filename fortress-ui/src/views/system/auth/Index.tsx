import { defineComponent, onMounted, ref, markRaw } from "vue";
import type { TreeNode, Auth } from "@/types"
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
        const pageInfo = ref<TreeNode<Auth>[]>([])
        const parentSelectorOptions = ref<TreeNode<Auth>[]>([])
        const searchKey = ref<string>('')
        const editDialogOpen = ref<boolean>(false)
        const editInfo = ref<Auth>(new AUTH_TEMPLATE())
        const editDialogTitle = ref<string>('')
        const formInstance = ref<FormInstance>()

        const onCreate = () => {
            editInfo.value = new AUTH_TEMPLATE()
            editDialogTitle.value = "新建权限"
            editDialogOpen.value = true
        }

        const onEdit = (index: number, row: TreeNode<Auth>) => {
            editDialogTitle.value = "编辑权限"
            editDialogOpen.value = true
            editInfo.value = row.record
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
                                loadAsTree()
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
                loadAsTree()
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

        const doUpdate = () => {
            AuthApi.update(editInfo.value).then(() => {
                onCancel()
                loadAsTree()
                ElMessage({
                    message: '更新成功！',
                    type: 'success',
                })
            }).catch(error => {
                console.log("add user occur error: ", error)
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
            AuthApi.getAsTree().then((result: TreeNode<Auth>[]) => {
                pageInfo.value = result
                parentSelectorOptions.value = result
                console.log(pageInfo.value)
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
                    default-expand-all
                >
                    <el-table-column type="selection" width="55" />
                    <el-table-column label="权限名称" width="180" prop="name" />
                    <el-table-column label="权限标识" width="180" prop="identify" />
                    <el-table-column label="序号" width="180" prop="orderNum" />
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

                <el-dialog v-model={editDialogOpen.value} title={editDialogTitle.value} v-slots={{
                    footer: () => (
                        <>
                            <el-button onClick={onCancel}>取消</el-button>
                            <el-button type="primary" onClick={doSubmit}>确定</el-button>
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
                            <el-tree-select
                                v-model={editInfo.value.parent}
                                data={parentSelectorOptions.value}
                                node-key="id"
                                render-after-expand={false}
                                default-checked-keys={editInfo.value.parent}
                                show-checkbox
                                props={{
                                    label: function (data: TreeNode<Auth>) {
                                        return data.record.name
                                    }
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