import { User } from "@/types"
import { defineStore } from "pinia"

const useGlobalStore = defineStore("global", {
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

export default useGlobalStore