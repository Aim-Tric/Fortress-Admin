import { defineComponent, reactive } from "vue";
import type { Role } from "@/types"
import { Column } from "element-plus";

export default defineComponent({
    setup() {
        const data = reactive({
            users: [
                {
                    id: "1",
                }
            ],
            searchKey: ""
        })
        const handleEdit = (index: number, row: Role) => {
            console.log(index, row)
        }
        const handleDelete = (index: number, row: Role) => {
            console.log(index, row)
        }
        const handleSelectionChange = () => {
            console.log()
        }
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
                    <el-table-column label="名称" width="180" property="password" />
                    <el-table-column label="操作" v-slots={
                        {
                            default: (scope: Column<Role>) => (
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
            </>
        )
    }
})