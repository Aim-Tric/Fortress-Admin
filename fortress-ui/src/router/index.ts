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
    await checkLogin(globalStore.$state.loginUser, to.name?.toString())

    if (!globalStore.loginUser) {
      currentUser().then(user => {
        globalStore.login(user)
      }).catch(error => error)
    }

  } catch (error) {
    redirectToLoginPageIfNecessary(to, from, next)
    return;
  }
  next()
})

export default router
