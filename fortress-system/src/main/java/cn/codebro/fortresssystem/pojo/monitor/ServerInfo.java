package cn.codebro.fortresssystem.pojo.monitor;

import cn.hutool.core.lang.Singleton;
import cn.hutool.system.*;
import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-25 16:56:04
 */
public class ServerInfo {
    private final String osVersion;
    private final String osArch;
    private final String osName;
    private final String hostName;
    private final String hostAddress;
    private final Float javaVersion;
    private final String javaVendor;

    private ServerInfo() {
        JavaInfo javaInfo = SystemUtil.getJavaInfo();
        this.javaVersion = javaInfo.getVersionFloat();
        this.javaVendor = javaInfo.getVendor();

        HostInfo hostInfo = SystemUtil.getHostInfo();
        this.hostName = hostInfo.getName();
        this.hostAddress = hostInfo.getAddress();

        OsInfo osInfo = SystemUtil.getOsInfo();
        this.osVersion = osInfo.getVersion();
        this.osName = osInfo.getName();
        this.osArch = osInfo.getArch();
    }

    public static ServerInfo getInstance() {
        return Singleton.get(ServerInfo.class);
    }

    public MemoryInfo getRealTimeMemoryInfo() {
        GlobalMemory memory = OshiUtil.getMemory();
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        return new MemoryInfo(runtimeInfo, memory);
    }

    public CpuInfo getRealTimeCPUInfo() {
        return OshiUtil.getCpuInfo();
    }

    public DiskInfo getRealTimeDiskInfo() {
        return new DiskInfo(OshiUtil.getDiskStores());
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getOsArch() {
        return osArch;
    }

    public String getOsName() {
        return osName;
    }

    public String getHostName() {
        return hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public Float getJavaVersion() {
        return javaVersion;
    }

    public String getJavaVendor() {
        return javaVendor;
    }

}
