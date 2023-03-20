package cn.codebro.fortress.system.pojo.monitor;

import cn.hutool.core.util.StrUtil;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;

import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-26 18:24:48
 */
public class DiskInfo {

    private final long total;
    private final List<HWDiskStore> diskStores;

    DiskInfo(List<HWDiskStore> diskStores) {
        this.diskStores = diskStores;
        long total = 0;
        for (HWDiskStore diskStore : diskStores) {
            total += diskStore.getSize();

            List<HWPartition> partitions = diskStore.getPartitions();
            for (HWPartition partition : partitions) {
                String type = partition.getType();
                System.out.println(StrUtil.format("{}({}): size:{}, type: {}",
                        partition.getName(), partition.getIdentification(), partition.getSize(), type));
            }
        }
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public List<HWDiskStore> getDiskStores() {
        return diskStores;
    }
}
