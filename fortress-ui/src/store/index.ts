import { Menu, SystemInfo, User } from "@/types"
import { defineStore } from "pinia"
import { RouteRecordRaw } from "vue-router"

const menuToVueRouter = (menus: Menu[]): Array<RouteRecordRaw> => {
    const routes: Array<RouteRecordRaw> = []
    if (menus && menus.length > 0) {
        menus.forEach((menu) => {
            recursionVisitMenu(menu, routes)
        })
    }
    return routes
}

const recursionVisitMenu = (menu: Menu, routes: Array<RouteRecordRaw>) => {
    if (menu.type === 1) {
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
        routes.push(route)
    }
    if (menu.children) {
        menu.children.forEach((m) => {
            recursionVisitMenu(m, routes)
        })
    }
}

export const useGlobalStore = defineStore("global", {
    state: (): { loginUser: User | undefined, loginDate: Date | undefined, lastActiveDate: Date | undefined, theme: string, initialized: boolean, routes: Array<RouteRecordRaw>, systemInfo: SystemInfo | undefined } => {
        return {
            loginUser: undefined,
            loginDate: undefined,
            lastActiveDate: undefined,
            theme: "default",
            initialized: false,
            routes: [],
            systemInfo: undefined
        }
    },
    actions: {
        initSystemInfo(systemInfo: SystemInfo) {
            this.systemInfo = systemInfo
        },
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
            handler?.(event)
        }
    }
})

export interface EventHandler {
    (event: Event): void;
}

export interface Event {
    name: string
    // eslint-disable-next-line
    getParam?(): Map<string, any>
}

export default {}