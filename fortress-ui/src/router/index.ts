import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import { isLogin, currentUser } from "@/api/User"
import type { UserVO } from "@/types"
import { useGlobalStore } from "@/store/index"

const routes: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login')
  },
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
    },
    {
      path: '/auth',
      name: 'Auth',
      component: () => import('@/views/system/auth/Index')
    },
    {
      path: '/menu',
      name: 'Menu',
      component: () => import('@/views/system/menu/Index')
    }]
  },
  {
    path: '/404',
    name: 'NotFound',
    component: () => import('@/views/404')
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NullPointerPage',
    redirect: '/404'
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach(async (to, from, next) => {
  const globalStore = useGlobalStore()
  if (!globalStore.$state.loginUser) {
    try {
      const login = await isLogin()
      if (!login && to.name !== 'Login') {
        next({ name: 'Login', query: { redirect: encodeURIComponent(to.path) } })
      }
      currentUser().then((user: UserVO) => {
        globalStore.login(user)
      })
    } catch (error) {
      if (to.name !== 'Login') {
        next({ name: 'Login', query: { redirect: encodeURIComponent(to.path) } })
      }
    }
  }
  next();
})

export default router
