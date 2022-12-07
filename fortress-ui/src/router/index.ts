import { createRouter, createWebHashHistory, NavigationGuardNext, RouteLocationNormalized, RouteRecordRaw } from 'vue-router'
import { currentUser, isLogin } from "@/api/User"
import { useGlobalStore } from "@/store/index"
import { User } from '@/types'

const routes: Array<RouteRecordRaw> = [{
  path: '/login',
  name: 'Login',
  component: () => import('@/views/Login')
}, {
  path: '/',
  redirect: '/home',
  name: 'StandardLayout',
  component: () => import('@/layout/AdminLayout/AdminLayout'),
  children: [
    { path: '/user-info', name: 'UserInfo', component: () => import('@/views/user/Index') },
    { path: '/home', name: 'Home', component: () => import('@/views/Index') },
    { path: '/user', name: 'UserManager', component: () => import('@/views/system/user/Index') },
    { path: '/role', name: 'RoleManager', component: () => import('@/views/system/role/Index') },
    { path: '/auth', name: 'AuthManager', component: () => import('@/views/system/auth/Index') },
    { path: '/menu', name: 'MenuManager', component: () => import('@/views/system/menu/Index') }
  ]
}, {
  path: '/404',
  name: 'NotFound',
  component: () => import('@/views/404')
}, {
  path: '/:pathMatch(.*)*',
  name: 'NullPointerPage',
  redirect: '/404'
}]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

function redirectToLoginPageIfNecessary(to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) {
  if (to.name !== 'Login') {
    next({ name: 'Login', query: { redirect: encodeURIComponent(to.path) } })
  }
  next()
}

async function checkLogin(loginUser: User | undefined, toName: string | undefined) {
  if (!loginUser && toName !== 'Login') {
    const login = await isLogin()
    if (!login) {
      throw new Error("not login!")
    }
  }
}

router.beforeEach(async (to, from, next) => {
  const globalStore = useGlobalStore()
  try {
    // 判断pinia是否存在用户信息
    await checkLogin(globalStore.$state.loginUser, to.name?.toString())

    // 如果不存在 尝试获取用户信息

    // 获取不到 跳转到登录页

    // 获取成功 直接跳转

    // 否则直接跳转
    if (!globalStore.loginUser) {
      const user = await currentUser()
      globalStore.login(user)
    }

  } catch (error) {
    redirectToLoginPageIfNecessary(to, from, next)
    return;
  }
  next()
})

export default router
