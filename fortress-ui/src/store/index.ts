import { Menu, User } from "@/types"
import { defineStore } from "pinia"
import { RouteRecordRaw } from "vue-router"

const menuToVueRouter = (menus: Menu[]): Array<RouteRecordRaw> => {
    const routes: Array<RouteRecordRaw> = []
    if (menus && menus.length > 0) {
        menus.forEach((menu) => {
            const route = recursionVisitMenu(menu)
            routes.push(route)
        })
    }
    return routes
}

const recursionVisitMenu = (menu: Menu): RouteRecordRaw => {
    const route: RouteRecordRaw = {
        path: menu.pagePath,
        name: menu.routeName,
        component: () => import(`@/views/${menu.componentPath}`),
        meta: {
            title: menu.pageTitle,
            description: menu.description
        },
        children: []
    }
    if (menu.hasChildren) {
        menu.children?.forEach((m) => {
            route.children.push(recursionVisitMenu(m))
        })
    }
    return route
}

export const useGlobalStore = defineStore("global", {
    state: (): { loginUser: User | undefined, loginDate: Date | undefined, lastActiveDate: Date | undefined, theme: string, initialized: boolean, routes: Array<RouteRecordRaw> } => {
        return {
            loginUser: undefined,
            loginDate: undefined,
            lastActiveDate: undefined,
            theme: "default",
            initialized: false,
            routes: []
        }
    },
    actions: {
        login(user: User) {
            this.loginUser = user
            this.loginDate = new Date()
            this.lastActiveDate = new Date()
        },
        initializeMenu(menus: Menu[]) {
            this.routes = menuToVueRouter(menus)
            this.initialized = true
        }
    },
})

export const useEventPool = defineStore("eventPool", {
    state: (): { handlers: Map<string, EventHandler> } => {
        return {
            handlers: new Map()
        }
    },
    actions: {
        subscribe(name: string, eventHandler: EventHandler) {
            this.handlers.set(name, eventHandler)
        },
        emit(event: Event) {
            const handler = this.handlers.get(event.name)
            handler?.handle(event)
        }
    }
})

export interface EventHandler {
    handle(event: Event): void;
}

export interface Event {
    name: string
    // eslint-disable-next-line
    getParam?(): Map<string, any>
}

export default {}