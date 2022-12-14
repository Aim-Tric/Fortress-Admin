import { defineComponent, reactive } from 'vue';
import { ElMessage } from 'element-plus';
import { login } from '@/api/User';
import { useRouter, useRoute } from 'vue-router';
import type { LoginUser } from '@/types'
import CryptoJS from 'crypto-js'
import './Login.css'

export default defineComponent({
    setup() {
        const $router = useRouter()
        const $route = useRoute()
        const loginForm: LoginUser = reactive({ loginId: '1', type: 'account', account: '', password: '', validateCode: '', rememberMe: false })

        const loginSuccess = () => {
            let redirect = '/'
            if ($route.query.redirect) {
                redirect = decodeURIComponent($route.query.redirect as string)
            }
            console.log($router.getRoutes())
            $router.replace({ path: redirect })
        }

        const doLogin = () => {
            loginForm.password = CryptoJS.enc.Base64.stringify(CryptoJS.MD5(loginForm.password))
            login(loginForm).then(() => {
                loginSuccess()
            }).catch(error => {
                console.log('login occur error: ', error)
                ElMessage({
                    message: error.message,
                    type: 'error',
                })
            })
        }

        return () => (
            <div class='full-page'>
                <div class='middle-outer-wrapper'>
                    <div class='middle-content-wrapper'>
                        <div class='middle-center-content'>
                            <div style={{ width: '350px' }}>
                                <h1 style={{ textAlign: 'center', marginBottom: '8px' }}>Fortress管理系统</h1>
                                <h2 style={{ textAlign: 'center' }}>登 录</h2>
                                <el-form model={loginForm} label-width='0px' size='large'>
                                    <el-form-item>
                                        <el-input v-model={loginForm.account} placeholder={`请输入${loginForm.type === 'account' ? '账号' : '手机号'}`} />
                                    </el-form-item>
                                    <el-form-item>
                                        <el-input v-model={loginForm.password} type='password' placeholder='请输入密码' />
                                    </el-form-item>
                                    <el-form-item>
                                        <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
                                            <el-checkbox v-model={loginForm.rememberMe} label='记住我' />
                                            <el-link type='primary'>忘记密码</el-link>
                                        </div>
                                    </el-form-item>
                                </el-form>
                                <div style={{ textAlign: 'center' }}>
                                    <el-button style={{ width: '100%' }} size='large' type='primary' onClick={doLogin}>登录</el-button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div >
        )
    }
})