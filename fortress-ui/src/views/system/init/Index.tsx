import { defineComponent, ref } from "vue";
import { updateSystemConfig, getSystemConfig } from "@/api/System";
import { SystemInfo } from "@/types";
import '@/views/Common.css'
import { useGlobalStore } from "@/store";

export default defineComponent({
    setup() {
        const globalStore = useGlobalStore()

        const initForm = ref<SystemInfo>({
            systemName: '',
            initialized: false,
            initializeTime: ''
        })

        const doInit = () => {
            updateSystemConfig(initForm.value).then(async () => {
                const systemInfo = await getSystemConfig()
                globalStore.initSystemInfo(systemInfo)
            })
        }

        return () => (
            <div class='full-page'>
                <div class='middle-outer-wrapper'>
                    <div class='middle-content-wrapper'>
                        <div class='middle-center-content'>
                            <div style={{ width: '350px' }}>
                                <h2 style={{ textAlign: 'center' }}>初始化系统</h2>
                                <el-form model={initForm} label-width='0px' size='large'>
                                    <el-form-item>
                                        <el-input v-model={initForm.value.systemName} placeholder={`请设置系统展示名称`} />
                                    </el-form-item>
                                </el-form>
                                <div style={{ textAlign: 'center' }}>
                                    <el-button style={{ width: '100%' }} size='large' type='primary' onClick={doInit}>初始化</el-button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div >
        )
    }
})