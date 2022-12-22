import { defineComponent } from "vue"
import { useRouter } from "vue-router"
import { getSystemConfig } from "./api/System"
import { useGlobalStore } from "./store"

export default defineComponent({
  setup() {
    const router = useRouter()
    const globalStore = useGlobalStore()

    if (!globalStore.systemInfo) {
      getSystemConfig().then((systemInfo) => {
        globalStore.initSystemInfo(systemInfo)
        if (!systemInfo.initialized) {
          router.push({ name: 'InitSystemInfo' })
        }
      })
    }

    return () => (
      <router-view />
    )
  }
})