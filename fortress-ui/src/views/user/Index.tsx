import { defineComponent, ref } from "vue"
import { User, Message, Phone } from "@element-plus/icons-vue"
import { useGlobalStore } from "@/store"
import "./Index.css"
import { updatePassword } from "@/api/User"


export default defineComponent({
    setup() {
        const globalStore = useGlobalStore()
        const user = globalStore.$state.loginUser

        const updatePasswordDialogOpen = ref<boolean>(false)
        const oldPasswordInputVal = ref<string>("")
        const newPasswordInputVal = ref<string>("")
        const newPasswordConfirmInputVal = ref<string>("")

        const doUpdatePassword = () => {
            user && updatePassword(user?.id, oldPasswordInputVal.value, newPasswordInputVal.value)
                .then(() => {
                    console.log("success!")
                })
                .catch((error) => {
                    console.log("error", error)
                })
        }

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
                            <el-col span={2}><el-button type="primary" link onClick={() => updatePasswordDialogOpen.value = true}>修改</el-button></el-col>
                        </el-row>
                        <el-row justify="space-around" style={{ marginBottom: ".75rem" }}>
                            <el-col span={22}><span style={{ marginRight: "1rem" }}>个性签名</span><span style={{ color: "rgb(168, 171, 178)" }}>这个人很懒，没有留下任何记号</span></el-col>
                            <el-col span={2}><el-button type="primary" link>修改</el-button></el-col>
                        </el-row>
                    </el-card>
                </el-col>

                <el-dialog v-model={updatePasswordDialogOpen.value} title="修改密码" width="30%" v-slots={{
                    footer: () => (
                        <>
                            <el-button onClick={() => updatePasswordDialogOpen.value = false}>取消</el-button>
                            <el-button type="primary" onClick={doUpdatePassword}>确定</el-button>
                        </>
                    )
                }}>
                    <el-form label-width="80px">
                        <el-form-item label="旧密码">
                            <el-input v-model={oldPasswordInputVal.value} placeholder="请输入旧密码" />
                        </el-form-item>
                        <el-form-item label="新密码">
                            <el-input v-model={newPasswordInputVal.value} placeholder="请输入新密码" />
                        </el-form-item>
                        <el-form-item label="确认新密码">
                            <el-input v-model={newPasswordConfirmInputVal.value} placeholder="请确认新密码" />
                        </el-form-item>
                    </el-form>
                </el-dialog>


            </el-row>
        )
    }
})