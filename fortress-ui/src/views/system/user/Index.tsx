import { defineComponent, onMounted, ref, markRaw } from "vue";
import type { User, Page } from "@/types"
import { Column, FormInstance, ElMessageBox, ElMessage } from "element-plus";
import { Delete } from '@element-plus/icons-vue'
import * as UserApi from "@/api/User"
import CryptoJS from 'crypto-js'

const SEX_MAP = new Map<number, string>([[0, "男"], [1, "女"], [2, "未知"]]);
const STATUS_MAP = new Map<number, string>([[0, "删除"], [1, "正常"], [2, "冻结"]]);
const STATUS_COLOR_MAP = new Map<number, string>([[0, "danger"], [1, ""], [2, "warning"]]);

class USER_TEMPLATER implements User {
    username = ''
    nickname = ''
    id = ''
    dept = ''
    email = ''
    password = ''
    phone = ''
    sex = 2
    post = ''
    role = ''
    orderNum = 0
}

export default defineComponent({
    setup() {
        const editDialogOpen = ref<boolean>(false)
        const pageInfo = ref<Page<User>>({
            records: [],
            current: 1,
            size: 10,
            total: 0
        })
        const searchKey = ref<string>("")
        const editDialogTitle = ref<string>("")
        const editInfo = ref<User>(new USER_TEMPLATER())
        const formInstance = ref<FormInstance>()

        const onCreate = () => {
            editDialogOpen.value = true
            editDialogTitle.value = "创建用户"
        }

        const onEdit = (index: number, row: User) => {
            editInfo.value = row
            editDialogOpen.value = true
            editDialogTitle.value = "编辑用户"
        }

        const onCancel = () => {
            editInfo.value = new USER_TEMPLATER()
            editDialogOpen.value = false
        }

        const doCreate = () => {
            editInfo.value.password = CryptoJS.enc.Base64.stringify(CryptoJS.MD5("123456"))
            UserApi.add(editInfo.value).then(() => {
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

        const onDelete = (index: number, row: User) => {
            ElMessageBox.confirm(
                `您将要删除昵称为${row.nickname}的用户，此操作将不可逆转，确定要删除吗？`,
                'Warning',
                {
                    type: 'warning',
                    icon: markRaw(Delete),
                    callback: (action: string) => {
                        if (action === 'confirm') {
                            UserApi.remove(row.id).then(() => {
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

        const handleSelectionChange = () => {
            console.log()
        }

        const loadByPage = () => {
            UserApi.page(pageInfo.value.current, pageInfo.value.size).then((result: Page<User>) => {
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
                <el-table data={pageInfo.value.records} style={{ width: '100%' }} onSelectionChange={handleSelectionChange} center>
                    <el-table-column type="selection" width="55" />
                    <el-table-column label="用户名" v-slots={
                        {
                            default: (scope: Column<User>) => (
                                <div style="display: flex; align-items: center">
                                    <span style="margin-left: 10px">{scope.row.username}</span>
                                </div>
                            )
                        }
                    } />
                    <el-table-column label="密码" width="80" property="password" />
                    <el-table-column label="昵称" property="nickname" />
                    <el-table-column label="性别" width="50" property="sex" v-slots={{
                        default: (scope: Column<User>) => (
                            <el-tag>{SEX_MAP.get(scope.row.sex)}</el-tag>
                        )
                    }} />
                    <el-table-column label="手机号" width="100" property="phone" />
                    <el-table-column label="邮箱地址" property="email" />
                    <el-table-column label="账号状态" v-slots={
                        {
                            default: (scope: Column<User>) => (
                                <el-tag type={STATUS_COLOR_MAP.get(scope.row.status)}>{STATUS_MAP.get(scope.row.status)}</el-tag>
                            )
                        }
                    } />
                    <el-table-column label="操作" center v-slots={
                        {
                            default: (scope: Column<User>) => (
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
                        <el-form-item label="用户名">
                            <el-input v-model={editInfo.value.username} placeholder="请输入用户登录账号" />
                        </el-form-item>
                        <el-form-item label="密码">
                            <el-input v-model={editInfo.value.password} type="password" placeholder="请输入用户登录密码" />
                        </el-form-item>
                        <el-form-item label="手机号">
                            <el-input v-model={editInfo.value.phone} placeholder="请输入手机号" />
                        </el-form-item>
                        <el-form-item label="邮箱地址">
                            <el-input v-model={editInfo.value.email} placeholder="请输入邮箱地址" />
                        </el-form-item>
                        <el-form-item label="昵称">
                            <el-input v-model={editInfo.value.nickname} placeholder="请输入用户昵称" />
                        </el-form-item>
                        <el-form-item label="性别">
                            <el-select v-model={editInfo.value.sex} placeholder="请选择性别">
                                <el-option label="男" value={0} />
                                <el-option label="女" value={1} />
                                <el-option label="未知" value={2} />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="用户角色">
                            <el-select v-model={editInfo.value.role} placeholder="请选择用户角色">
                            </el-select>
                        </el-form-item>
                        <el-form-item label="归属部门">
                            <el-select v-model={editInfo.value.dept} placeholder="请选择用户归属部门">
                            </el-select>
                        </el-form-item>
                        <el-form-item label="用户岗位">
                            <el-select v-model={editInfo.value.post} placeholder="请选择用户所在岗位">
                            </el-select>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </>
        )
    }
})