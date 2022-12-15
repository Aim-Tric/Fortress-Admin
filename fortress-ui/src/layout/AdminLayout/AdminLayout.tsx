import { defineComponent, ref } from 'vue';
import { useRouter } from 'vue-router';
import AdminMenu from './modules/AdminMenu';
import { useGlobalStore } from '@/store';
import { ElMessage } from 'element-plus';
import { logout as logoutApi } from '@/api/User'
import { Fold } from "@element-plus/icons-vue"
import './AdminLayout.css'

export const Logo = defineComponent({
    props: {
        collapse: {
            type: Boolean,
            required: false,
            default: false
        }
    },
    setup(props) {
        const collapse = props.collapse ? 'collapse' : ''
        return () => (
            <div class={`logo ${collapse}`}>
                <el-image style={{ width: '56px', height: '56px' }} src='https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'></el-image>
                {!props.collapse && (<span>Fotress管理系统</span>)}
            </div>
        )
    }
})

export default defineComponent({
    setup() {
        const globalStore = useGlobalStore()
        const $router = useRouter()

        const collapse = ref<boolean>(false)

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
                <el-aside width='220px' style={{ minHeight: '100vh' }}>
                    <Logo collapse={collapse.value} />
                    <AdminMenu collapse={collapse.value} style={{ height: 'calc(100vh - 56px)' }} />
                </el-aside>
                <el-container>
                    <el-header style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                        <el-button icon={Fold} circle onClick={() => { collapse.value = !collapse.value; console.log(collapse.value) }}></el-button>
                        <el-dropdown v-slots={{
                            dropdown: () => (
                                <el-dropdown-menu>
                                    <el-dropdown-item><el-link underline={false} onClick={() => $router.push({ path: '/user-info' })}>个人中心</el-link></el-dropdown-item>
                                    <el-dropdown-item><el-link underline={false} onClick={logout}>退出登录</el-link></el-dropdown-item>
                                </el-dropdown-menu>
                            )
                        }}>
                            <div style={{ display: 'inline-flex', alignItems: 'center' }}>
                                <el-avatar src='https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png' />
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