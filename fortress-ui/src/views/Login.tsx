import { defineComponent, reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import SelectTabCom from '@/components/SelectTab';
import type { SelectTab } from '@/components/SelectTab';
import { login } from '@/api/User';
import { useRouter, useRoute } from 'vue-router';
import type { LoginUser } from '@/types'
import CryptoJS from 'crypto-js'
import './Login.css'

const loginType: SelectTab[] = [
    { key: 'account', name: '账号' },
    { key: 'phone', name: '手机号' },
    // { key: 'third', name: '第三方' },
]

// 检查是否为账号和手机号登录
const isAccountLogin = (type: string) => {
    return ['account', 'phone'].includes(type)
}

export const PhoneValidateCodeCom = defineComponent({
    props: {
        validateCode: String
    },
    setup(props, { emit }) {
        const data = reactive({ ...props, timemilis: 60, ban: false })
        const inputDom = ref<HTMLElement>();
        // 获取手机动态验证码
        let fecthCodeTimer: NodeJS.Timer
        const getDynamicValidateCode = () => {
            if (data.ban) {
                return
            }
            inputDom.value?.focus()
            data.ban = true
            fecthCodeTimer = setInterval(() => {
                if (data.timemilis-- === 1) {
                    clearTimeout(fecthCodeTimer)
                    data.ban = false
                    data.timemilis = 60
                }
            }, 1000)
        }

        return () => (
            <el-input ref={inputDom} v-model={data.validateCode} onChange={() => emit('update-validateCode', data.validateCode)} v-slots={
                {
                    append: () => (
                        <el-button size='default' onClick={getDynamicValidateCode} class={data.ban ? 'ban' : ''}>获取动态验证码{data.ban && `${data.timemilis}s`}</el-button>
                    )
                }
            } />
        )
    }
})

export default defineComponent({
    setup() {
        const $router = useRouter()
        const $route = useRoute()
        const loginForm: LoginUser = reactive({ loginId: '1', type: 'account', account: '', password: '', validateCode: '' })

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
                            <div style={{ fontSize: '18px', textAlign: 'center', marginBottom: '8px' }}>XXX管理系统</div>
                            <div>
                                <h2 style={{ textAlign: 'center' }}>登 录</h2>

                                <SelectTabCom model={loginType} />
                                {
                                    (isAccountLogin(loginForm.type) &&

                                        <el-form model={loginForm} label-width='60px' size='default'>
                                            <el-form-item label={loginForm.type === 'account' ? '账号' : '手机号'}>
                                                <el-input v-model={loginForm.account} />
                                            </el-form-item>
                                            {
                                                (loginForm.type === 'account' &&
                                                    <el-form-item label='密码' >
                                                        <el-input v-model={loginForm.password} type='password' />
                                                    </el-form-item>)

                                                ||

                                                (loginForm.type === 'phone' &&
                                                    <el-form-item label='验证码' >
                                                        <PhoneValidateCodeCom v-model={loginForm.validateCode} />
                                                    </el-form-item>)
                                            }
                                        </el-form>)
                                    ||
                                    (<div>第三方登录入口</div>)
                                }
                                {
                                    (isAccountLogin(loginForm.type) &&
                                        <div style={{ textAlign: 'center' }}>
                                            <el-button size='default' type='primary' onClick={doLogin}>登录</el-button>
                                        </div>)
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div >
        )
    }
})