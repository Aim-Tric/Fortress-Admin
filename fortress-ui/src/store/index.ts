import { User } from "@/types"
import { defineStore } from "pinia"

export const useGlobalStore = defineStore("global", {
    state: (): { loginUser: User | undefined, loginDate: Date | undefined, lastActiveDate: Date | undefined, theme: string, initialized: boolean } => {
        return {
            loginUser: undefined,
            loginDate: undefined,
            lastActiveDate: undefined,
            theme: "default",
            initialized: false
        }
    },
    actions: {
        login(user: User) {
            this.loginUser = user
            this.initialized = true
            this.loginDate = new Date()
            this.lastActiveDate = new Date()
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