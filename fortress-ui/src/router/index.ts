import { createRouter, createWebHashHistory, NavigationGuardNext, RouteLocationNormalized, RouteRecordRaw } from 'vue-router'
import { currentUser, isLogin } from "@/api/User"
import { useGlobalStore } from "@/store/index"
import { User } from '@/types'
import { getSystemConfig } from '@/api/System'

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
  path: '/init',
  name: 'InitSystemInfo',
  component: () => import('@/views/system/init/Index')
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

const notLoginNameList = ['Login', 'InitSystemInfo']

function redirectToLoginPageIfNecessary(to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) {
  if (to.name && !notLoginNameList.includes(to.name.toString())) {
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
    let systemInfo = globalStore.systemInfo
    if (!systemInfo) {
      systemInfo = await getSystemConfig()
      globalStore.initSystemInfo(systemInfo)
    }
    if (!systemInfo.initialized && to.name !== 'InitSystemInfo') {
      next({ name: 'InitSystemInfo' })
      return
    }

    await checkLogin(globalStore.$state.loginUser, to.name?.toString())

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
