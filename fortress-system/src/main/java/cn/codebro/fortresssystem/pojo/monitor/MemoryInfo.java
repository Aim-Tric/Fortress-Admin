package cn.codebro.fortresssystem.pojo.monitor;

import cn.hutool.system.RuntimeInfo;
import oshi.hardware.GlobalMemory;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-26 00:13:52
 */
public class MemoryInfo {
    private final long maxMemory;
    private final long totalMemory;
    private final long freeMemory;
    private final long usableMemory;
    private final long machineTotalMemory;
    private final long machineMemoryPageSize;
    private final long machineAvailableMemory;

    MemoryInfo(RuntimeInfo runtimeInfo, GlobalMemory memory) {
        this.maxMemory = runtimeInfo.getMaxMemory();
        this.totalMemory = runtimeInfo.getTotalMemory();
        this.freeMemory = runtimeInfo.getFreeMemory();
        this.usableMemory = runtimeInfo.getUsableMemory();

        this.machineTotalMemory = memory.getTotal();
        this.machineMemoryPageSize = memory.getPageSize();
        this.machineAvailableMemory = memory.getAvailable();
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public long getUsableMemory() {
        return usableMemory;
    }

    public long getMachineTotalMemory() {
        return machineTotalMemory;
    }

    public long getMachineMemoryPageSize() {
        return machineMemoryPageSize;
    }

    public long getMachineAvailableMemory() {
        return machineAvailableMemory;
    }
}
