import { defineComponent, ref } from "vue";
import { getServerInfo } from "@/api/Monitor";
import { ServerInfo } from "@/types";

export default defineComponent({
    setup() {
        const serverInfo = ref<ServerInfo>({
            osArch: '',
            hostAddress: '',
            hostName: '',
            javaVendor: '',
            javaVersion: 0,
            osName: '',
            osVersion: '',
            realTimeCPUInfo: {
                cpuModel: '',
                cpuNum: 0
            },
            realTimeMemoryInfo: {
                freeMemory: 0,
                machineAvailableMemory: 0,
                machineMemoryPageSize: 0,
                machineTotalMemory: 0,
                maxMemory: 0,
                totalMemory: 0,
                usableMemory: 0
            }
        })
        getServerInfo().then((data) => {
            console.log(data)
            serverInfo.value = data
        })
        return () => (
            <el-row gutter={4}>
                <el-col span={12}>
                    <el-card class="box-card" v-slots={{
                        header: () => (
                            <div class="card-header">
                                <span>系统软件信息</span>
                            </div>
                        )
                    }}>
                        <div>
                            <span>系统架构</span>
                            <p>{serverInfo.value.osArch}</p>
                        </div>
                        <div>
                            <span>系统名称</span>
                            <p>{serverInfo.value.osName}</p>
                        </div>
                        <div>
                            <span>系统版本</span>
                            <p>{serverInfo.value.osVersion}</p>
                        </div>
                        <div>
                            <span>Java发行商</span>
                            <p>{serverInfo.value.javaVendor}</p>
                        </div>
                        <div>
                            <span>Java版本</span>
                            <p>{serverInfo.value.javaVersion}</p>
                        </div>
                        <div>
                            <span>主机地址</span>
                            <p>{serverInfo.value.hostAddress}</p>
                        </div>
                        <div>
                            <span>主机名称</span>
                            <p>{serverInfo.value.hostName}</p>
                        </div>
                    </el-card>
                </el-col>
                <el-col span={12}>
                    <el-card class="box-card" v-slots={{
                        header: () => (
                            <div class="card-header">
                                <span>系统硬件信息</span>
                            </div>
                        )
                    }}>
                        <div>
                            <span>CPU信息</span>
                            <p>{serverInfo.value.realTimeCPUInfo.cpuModel}</p>
                        </div>
                        <div>
                            <span>CPU核心数</span>
                            <p>{serverInfo.value.realTimeCPUInfo.cpuNum}</p>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        )
    }
})
