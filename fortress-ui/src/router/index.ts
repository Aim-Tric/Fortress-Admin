import { createRouter, createWebHashHistory, NavigationGuardNext, RouteLocationNormalized, RouteRecordRaw } from 'vue-router'
import { getUserMenus, isLogin } from "@/api/User"
import { useEventPool, useGlobalStore } from "@/store/index"

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login')
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'ToLogin',
    redirect: '/login'
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

function getRoutes(routes: Array<RouteRecordRaw>) {
  return [{
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login')
  }, {
    path: '/',
    redirect: '/home',
    name: 'StandardLayout',
    component: () => import('@/layout/AdminLayout/AdminLayout'),
    children: [...routes]
  }, {
    path: '/404',
    name: 'NotFound',
    component: () => import('@/views/404')
  }, {
    path: '/:pathMatch(.*)*',
    name: 'NullPointerPage',
    redirect: '/404'
  }]
}

export function initializeDynamicRoutes(rs: Array<RouteRecordRaw>): void {
  for (const route of router.getRoutes()) {
    if (route.name) {
      router.removeRoute(route.name)
    }
  }
  const routes = getRoutes(rs)
  for (const route of routes) {
    router.addRoute(route)
  }
}

router.beforeEach(async (to, from, next) => {
  const globalStore = useGlobalStore()
  const eventPool = useEventPool()
  console.log("?????")
  if (!globalStore.$state.loginUser && to.name !== 'Login') {
    try {
      const login = await isLogin()
      console.log("?????")
      if (!login) {
        throw new Error("not login!")
      }
      eventPool.emit({ name: "LoadUser" })
      console.log("init? ", globalStore.$state.initialized)
      if (!globalStore.$state.initialized) {
        const data = await getUserMenus()
        globalStore.initializeMenu(data)
        initializeDynamicRoutes(globalStore.$state.routes)
        console.log("init done")
      }
    } catch (error) {
      redirectToLoginPageIfNecessary(to, from, next)
      return;
    }
  }
  console.log("do next")
  next()
})

export default router
