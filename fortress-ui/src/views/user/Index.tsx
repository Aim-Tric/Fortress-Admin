import { defineComponent } from "vue"
import { User, Message, Phone } from "@element-plus/icons-vue"
import { useGlobalStore } from "@/store"
import "./Index.css"

export default defineComponent({
    setup() {
        const globalStore = useGlobalStore()
        const user = globalStore.$state.loginUser
        return () => (
            <el-row gutter={12}>
                <el-col span={12}>
                    <el-card class="box-card" v-slots={{
                        header: () =>
                            <div class="card-header">
                                <span>个人信息</span>
                            </div>
                    }}>
                        <div style={{ display: 'flex', alignItems: 'center' }}>
                            <div style={{ textAlign: 'center', marginRight: '1rem' }}>
                                <el-image style="width: 150px; height: 150px" src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg" fit="contain" />
                            </div>
                            <div>
                                <el-row gutter={2} style={{ marginBottom: ".75rem" }}>
                                    <el-col span={4}><el-icon><User /></el-icon></el-col>
                                    <el-col span={20}><span>{user?.nickname}</span></el-col>
                                </el-row>
                                <el-row gutter={2} style={{ marginBottom: ".75rem" }}>
                                    <el-col span={4}><el-icon><Message /></el-icon></el-col>
                                    <el-col span={20}><span>{user?.email}</span></el-col>
                                </el-row>
                                <el-row gutter={2} style={{ marginBottom: ".75rem" }}>
                                    <el-col span={4}><el-icon><Phone /></el-icon></el-col>
                                    <el-col span={20}><span>{user?.phone}</span></el-col>
                                </el-row>
                            </div>
                        </div>
                    </el-card>
                </el-col>

                <el-col span={12}>
                    <el-card class="box-card" v-slots={{
                        header: () =>
                            <div class="card-header">
                                <span>账号管理</span>
                            </div>
                    }}>
                        <el-row justify="space-around" style={{ marginBottom: ".75rem" }}>
                            <el-col span={22}><span style={{ marginRight: "1rem" }}>账户密码</span><span style={{ color: "rgb(168, 171, 178)" }}>当前强度：弱</span></el-col>
                            <el-col span={2}><el-button type="primary" link>修改</el-button></el-col>
                        </el-row>
                        <el-row justify="space-around" style={{ marginBottom: ".75rem" }}>
                            <el-col span={22}><span style={{ marginRight: "1rem" }}>个性签名</span><span style={{ color: "rgb(168, 171, 178)" }}>这个人很懒，没有留下任何记号</span></el-col>
                            <el-col span={2}><el-button type="primary" link>修改</el-button></el-col>
                        </el-row>
                    </el-card>
                </el-col>
            </el-row>
        )
    }
})