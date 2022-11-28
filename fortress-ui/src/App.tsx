import { defineComponent } from "vue"
import { currentUser, getUserMenus } from "./api/User"
import { initializeDynamicRoutes } from "./router"
import { useGlobalStore, useEventPool } from "./store"
import { UserVO } from "./types"

export default defineComponent({
  setup() {
    const globalStore = useGlobalStore()
    const eventPool = useEventPool()

    eventPool.subscribe("LoginSuccess", {
      handle: async () => {
        if (!globalStore.$state.initialized) {
          const data = await getUserMenus()
          globalStore.initializeMenu(data)
          initializeDynamicRoutes(globalStore.$state.routes)
        }
      }
    })

    eventPool.subscribe("LoadUser", {
      handle: () => {
        currentUser().then((user: UserVO) => {
          globalStore.login(user)
        })
      }
    })

    return () => (
      <router-view />
    )
  }
})