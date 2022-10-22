import { defineComponent, reactive, ref } from "vue";
import SelectTabCom from "@/components/SelectTab";
import type { SelectTab } from "@/components/SelectTab";
import { login } from "@/api/User";
import router from "@/router";
import { useRouter } from "vue-router";
import type { ApiResult, User, LoginUser } from "@/types"
import './Login.css'

const loginType: SelectTab[] = [
    { key: "account", name: '账号' },
    { key: "phone", name: '手机号' },
    { key: "third", name: '第三方' },
]

// 检查是否为账号和手机号登录
const isAccountLogin = (type: string) => {
    return ['account', 'phone'].includes(type)
}

// 第三方登录 检查loginId是否已经验证成功
let count = 0;
const checkLoginIsSuccess = (loginId: string) => {
    // 登录检查
    console.log("检查loginId是否已经验证成功", loginId)
    return count++ > 10
}

// 根据登录用户生成对应的动态路由
const handleDynamicRouter = () => {
    // auto redirect to the index page and handle the activate dynamic router generation;
    console.log("根据登录用户生成对应的动态路由")

    const routes = [
        {
            path: '/',
            redirect: '/home',
            name: 'StandardLayout',
            component: () => import('@/layout/AdminLayout/AdminLayout'),
            children: [{
                path: '/home',
                name: 'Home',
                component: () => import('@/views/Index')
            },
            {
                path: '/user',
                name: 'User',
                component: () => import('@/views/system/user/Index')
            },
            {
                path: '/role',
                name: 'Role',
                component: () => import('@/views/system/role/Index')
            }]
        }
    ]
    routes.map(item => router.addRoute(item))
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
            <el-input ref={inputDom} v-model={data.validateCode} onChange={() => emit("update-validateCode", data.validateCode)} v-slots={
                {
                    append: () => (
                        <el-button size="default" onClick={getDynamicValidateCode} class={data.ban ? 'ban' : ''}>获取动态验证码{data.ban && `${data.timemilis}s`}</el-button>
                    )
                }
            } />
        )
    }
})

export default defineComponent({
    setup() {
        const $router = useRouter();
        const loginForm: LoginUser = reactive({ loginId: "1", type: 'account', account: '', password: '', validateCode: '' })
        let timer: NodeJS.Timer | undefined
        const onSelectTabItemChange = (item: SelectTab) => {
            if (!isAccountLogin(item.key)) {
                !timer &&
                    (timer = setInterval(() => {
                        if (checkLoginIsSuccess(loginForm.loginId)) {
                            clearInterval(timer)
                            timer = undefined
                            handleDynamicRouter()
                        }
                    }, 800))
            }
            loginForm.type = item.key
        }
        const doLogin = () => {
            login(loginForm).then((resp: ApiResult<User>) => {
                console.log("result", resp)
                handleDynamicRouter()
                $router.push({ path: '/' })
            })
        }
        return () => (
            <div style="position: related;">
                <el-row style={{ top: '300px' }}>
                    <el-col span={8} offset={12}>
                        <el-card>
                            <div style={{ fontSize: '18px', textAlign: 'center', marginBottom: '8px' }}>XXX管理系统</div>
                            <SelectTabCom model={loginType} onItemChange={onSelectTabItemChange} />
                            {
                                (isAccountLogin(loginForm.type) &&

                                    <el-form model={loginForm} label-width="60px" size="default">
                                        <el-form-item label={loginForm.type === 'account' ? '账号' : '手机号'}>
                                            <el-input v-model={loginForm.account} />
                                        </el-form-item>
                                        {
                                            (loginForm.type === 'account' &&
                                                <el-form-item label="密码" >
                                                    <el-input v-model={loginForm.password} type="password" />
                                                </el-form-item>)

                                            ||

                                            (loginForm.type === 'phone' &&
                                                <el-form-item label="验证码" >
                                                    <PhoneValidateCodeCom v-model={loginForm.validateCode} />
                                                </el-form-item>)
                                        }
                                    </el-form>)
                                ||
                                (<div>第三方登录入口</div>)
                            }
                            {
                                (isAccountLogin(loginForm.type) &&
                                    <div style={{ textAlign: "center" }}>
                                        <el-button size="default" type="primary" onClick={doLogin}>登录</el-button>
                                    </div>)
                            }
                        </el-card>
                    </el-col>
                </el-row>
            </div >
        )
    }
})