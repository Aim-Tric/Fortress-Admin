import { defineComponent, nextTick, onMounted, ref } from 'vue';
import { getServerInfo } from '@/api/Monitor';
import { ServerInfo } from '@/types';
import Echarts from '@/components/Echart/Echarts';

export default defineComponent({
    components: {
        Echarts
    },
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
                cpuNum: 0,
                free: 0,
                sys: 0,
                toTal: 0,
                user: 0,
                wait: 0
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
        const cpuUseRateChart = ref()
        const cpuUseRateOption = ref()
        onMounted(() => {
            nextTick(() => {
                cpuUseRateChart.value.showLoading()
                getServerInfo().then((data) => {
                    console.log("serverInfo", data)
                    serverInfo.value = data
                    console.log("chart ref", cpuUseRateChart)
                    cpuUseRateOption.value = {
                        series: [
                            {
                                type: 'pie',
                                stillShowZeroSum: false,
                                data: [
                                    {
                                        value: data.realTimeCPUInfo.sys,
                                        name: '系统使用率'
                                    },
                                    {
                                        value: data.realTimeCPUInfo.user,
                                        name: '用户使用率'
                                    },
                                    {
                                        value: data.realTimeCPUInfo.wait,
                                        name: '当前等待率'
                                    },
                                    {
                                        value: data.realTimeCPUInfo.free,
                                        name: '当前空闲率'
                                    }
                                ],
                                radius: ['40%', '70%'],
                            }
                        ]
                    }
                    cpuUseRateChart.value.setOption(cpuUseRateOption.value)
                })
            })
        })

        return () => (
            <el-row gutter={24}>
                <el-col span={12}>
                    <el-card class='box-card' v-slots={{
                        header: () => (
                            <div class='card-header'>
                                <span>系统软件信息</span>
                            </div>
                        )
                    }}>
                        <el-form label-width='120px'>
                            <el-form-item label='系统架构'>
                                <span>{serverInfo.value.osArch}</span>
                            </el-form-item>
                            <el-form-item label='系统名称'>
                                <span>{serverInfo.value.osName}</span>
                            </el-form-item>
                            <el-form-item label='系统版本'>
                                <span>{serverInfo.value.osVersion}</span>
                            </el-form-item>
                            <el-form-item label='Java发行商'>
                                <span>{serverInfo.value.javaVendor}</span>
                            </el-form-item>
                            <el-form-item label='Java版本'>
                                <span>{serverInfo.value.javaVersion}</span>
                            </el-form-item>
                            <el-form-item label='主机地址'>
                                <span>{serverInfo.value.hostAddress}</span>
                            </el-form-item>
                            <el-form-item label='主机名称'>
                                <span>{serverInfo.value.hostName}</span>
                            </el-form-item>
                        </el-form>
                    </el-card>
                </el-col>
                <el-col span={12}>
                    <el-card class='box-card' v-slots={{
                        header: () => (
                            <div class='card-header'>
                                <span>服务器CPU情况信息</span>
                            </div>
                        )
                    }}>
                        <el-form label-width='120px'>
                            <el-form-item label='CPU信息'>
                                <span>{serverInfo.value.realTimeCPUInfo.cpuModel}</span>
                            </el-form-item>
                            <el-form-item label='CPU核心数'>
                                <span>{serverInfo.value.realTimeCPUInfo.cpuNum}</span>
                            </el-form-item>
                            <el-form-item label='CPU使用情况'>
                                <echarts ref={cpuUseRateChart} option={cpuUseRateOption.value} style={{ width: '400px', height: '250px' }}></echarts>
                            </el-form-item>
                        </el-form>
                    </el-card>
                </el-col>
                <el-col span={12}>
                    <el-card class='box-card' v-slots={{
                        header: () => (
                            <div class='card-header'>
                                <span>服务器内存情况信息</span>
                            </div>
                        )
                    }}>
                        <el-form label-width='120px'>
                            <el-form-item label='JVM最大内存'>
                                <span>{serverInfo.value.realTimeMemoryInfo.maxMemory} bytes</span>
                            </el-form-item>
                            <el-form-item label='JVM最大可用内存'>
                                <span>{serverInfo.value.realTimeMemoryInfo.totalMemory} bytes</span>
                            </el-form-item>
                            <el-form-item label='JVM已分配内存'>
                                <span>{serverInfo.value.realTimeMemoryInfo.usableMemory} bytes</span>
                            </el-form-item>
                            <el-form-item label='JVM已分配内存中的剩余空间'>
                                <span>{serverInfo.value.realTimeMemoryInfo.freeMemory} bytes</span>
                            </el-form-item>
                            <el-form-item label='机器物理内存'>
                                <span>{serverInfo.value.realTimeMemoryInfo.machineTotalMemory} bytes</span>
                            </el-form-item>
                            <el-form-item label='机器可用内存'>
                                <span>{serverInfo.value.realTimeMemoryInfo.machineAvailableMemory} bytes</span>
                            </el-form-item>
                            <el-form-item label='机器内存页大小'>
                                <span>{serverInfo.value.realTimeMemoryInfo.machineMemoryPageSize} bytes</span>
                            </el-form-item>
                        </el-form>
                    </el-card>
                </el-col>
            </el-row>
        )
    }
})
