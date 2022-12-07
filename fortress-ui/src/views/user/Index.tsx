import { defineComponent } from "vue"
import { User, Message, Phone } from "@element-plus/icons-vue"
import { useGlobalStore } from "@/store"
import "./Index.css"

export default defineComponent({
    setup() {
        const globalStore = useGlobalStore()
        const user = globalStore.$state.loginUser
        return () => (
            <el-card class="box-card" v-slots={{
                header: () =>
                    <div class="card-header">
                        <span>个人信息</span>
                        <el-button class="button" text>编辑</el-button>
                    </div>
            }}>
                <div>
                    <el-row gutter={2} justify="center" align="middle">
                        <el-col span={4}>
                            <el-image style="width: 150px; height: 150px" src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg" fit="contain" />
                        </el-col>
                        <el-col span={20}>
                            <el-row gutter={2}>
                                <el-col span={1}><el-icon><User /></el-icon></el-col>
                                <el-col span={23}><span>{user?.nickname}</span></el-col>
                            </el-row>
                            <el-row gutter={2}>
                                <el-col span={1}><el-icon><Message /></el-icon></el-col>
                                <el-col span={23}><span>{user?.email}</span></el-col>
                            </el-row>
                            <el-row gutter={2}>
                                <el-col span={1}><el-icon><Phone /></el-icon></el-col>
                                <el-col span={23}><span>{user?.phone}</span></el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </div>
            </el-card>
        )
    }
})