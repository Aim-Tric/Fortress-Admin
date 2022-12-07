import { defineComponent } from "vue";
import { useRouter } from "vue-router";
import AdminMenu from "./modules/AdminMenu";
import { useGlobalStore } from "@/store";
import { ElMessage } from "element-plus";
import { logout as logoutApi } from "@/api/User"

export default defineComponent({
    setup() {
        const globalStore = useGlobalStore()
        const $router = useRouter()
        const logout = () => {
            logoutApi().then(() => {
                ElMessage({
                    message: '已退出登录',
                    type: 'success',
                })
                $router.replace({ name: 'Login' })
            })
        }
        return () => (
            <el-container>
                <el-aside width="220px"><AdminMenu /></el-aside>
                <el-container>
                    <el-header style={{ display: 'flex', justifyContent: 'flex-end' }}>
                        <el-dropdown v-slots={{
                            dropdown: () => (
                                <el-dropdown-menu>
                                    <el-dropdown-item><el-link underline={false} onClick={() => $router.push({ path: "/user-info" })}>个人中心</el-link></el-dropdown-item>
                                    <el-dropdown-item><el-link underline={false} onClick={logout}>退出登录</el-link></el-dropdown-item>
                                </el-dropdown-menu>
                            )
                        }}>
                            <div style={{ display: 'inline-flex', alignItems: 'center' }}>
                                <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
                                <span>{globalStore.$state.loginUser?.username}</span>
                            </div>
                        </el-dropdown>
                    </el-header>
                    <el-main>
                        <router-view />
                    </el-main>
                </el-container>
            </el-container>
        )
    }
})