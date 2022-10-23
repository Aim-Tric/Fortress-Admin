import { defineComponent, reactive, onMounted } from "vue";
import type { ApiResult, User, Page } from "@/types"
import { Column } from "element-plus";
import * as UserApi from "@/api/User"


export default defineComponent({
    setup() {
        const data = reactive({
            users: [
                {
                    id: "1",
                    username: "Guowentao",
                    password: "********",
                    nickname: "Tric",
                    sex: 0,
                    phone: "187****2384",
                    email: "473074764@qq.com",
                    status: 1,
                    order: 0
                }
            ],
            searchKey: "",
            page: 1,
            pageSize: 10,
        })

        const handleEdit = (index: number, row: User) => {
            console.log(index, row)
        }

        const handleDelete = (index: number, row: User) => {
            console.log(index, row)
        }

        const handleSelectionChange = () => {
            console.log()
        }

        onMounted(() => {
            UserApi.page(data.page, data.pageSize).then((result: ApiResult<Page<User>>) => {
                console.log(result)
            })
        })

        return () => (
            <>
                <el-row style={{ marginBottom: '10px' }} justify="space-between">
                    <el-col span={4}>
                        <el-button-group>
                            <el-button type="primary">新增</el-button>
                            <el-button type="success">修改</el-button>
                            <el-button type="danger">删除</el-button>
                        </el-button-group>
                    </el-col>
                    <el-col span={4}>
                        <el-input v-model={data.searchKey} placeholder="请输入搜索条件" clearable v-slots={{
                            append: () => (
                                <el-button icon="Search" />
                            )
                        }} />
                    </el-col>
                </el-row>
                <el-table data={data.users} style="width: 100%" onSelectionChange={handleSelectionChange}>
                    <el-table-column type="selection" width="55" />
                    <el-table-column label="用户名" width="180" v-slots={
                        {
                            default: (scope: Column<User>) => (
                                <div style="display: flex; align-items: center">
                                    <span style="margin-left: 10px">{scope.row.username}</span>
                                </div>
                            )
                        }
                    } />
                    <el-table-column label="密码" width="180" property="password" />
                    <el-table-column label="昵称" width="180" property="nickname" />
                    <el-table-column label="性别" width="50" property="sex" />
                    <el-table-column label="手机号" width="180" property="phone" />
                    <el-table-column label="邮箱地址" width="180" property="email" />
                    <el-table-column label="账号状态" width="180" v-slots={
                        {
                            default: (scope: Column<User>) => (
                                <el-tag type={scope.row.status === 1 ? '' : 'danger'}>{scope.row.status === 1 ? '正常' : '冻结'}</el-tag>
                            )
                        }
                    } />
                    <el-table-column label="操作" v-slots={
                        {
                            default: (scope: Column<User>) => (
                                <>
                                    <el-button onClick={handleEdit(scope.$index, scope.row)}
                                    >编辑</el-button>
                                    <el-button
                                        type="danger"
                                        onClick={handleDelete(scope.$index, scope.row)}
                                    >删除</el-button>
                                </>
                            )
                        }
                    }>
                    </el-table-column>
                </el-table>

                <el-pagination layout="prev, pager, next" total={1000}
                    onSizeChange="handleSizeChange"
                    onCurrentChange="handleCurrentChange" />
            </>
        )
    }
})