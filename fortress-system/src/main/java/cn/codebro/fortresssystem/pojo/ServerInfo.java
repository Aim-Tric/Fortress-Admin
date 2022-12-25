package cn.codebro.fortresssystem.pojo;

import cn.hutool.system.*;

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

    public ServerInfo() {
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

    public void getMemoryInfo() {
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        long maxMemory = runtimeInfo.getMaxMemory();
        long totalMemory = runtimeInfo.getTotalMemory();
        long freeMemory = runtimeInfo.getFreeMemory();
        long usableMemory = runtimeInfo.getUsableMemory();
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
