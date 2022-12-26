package cn.codebro.fortresssystem.pojo.monitor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.system.oshi.OshiUtil;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;

import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-26 18:24:48
 */
public class DiskInfo {

    private long total;

    DiskInfo() {
        List<HWDiskStore> diskStores = OshiUtil.getDiskStores();
        for (HWDiskStore diskStore : diskStores) {
            this.total += diskStore.getSize();

            long reads = diskStore.getReads();
            long writes = diskStore.getWrites();
            String model = diskStore.getModel();
            List<HWPartition> partitions = diskStore.getPartitions();
            for (HWPartition partition : partitions) {
                String type = partition.getType();
                System.out.println(StrUtil.format("{}({}): size:{}, type: {}",
                        partition.getName(), partition.getIdentification(), partition.getSize(), type));
            }
            System.out.println(StrUtil.format("{}|{}: reads:{}, writes:{}, total:{}", diskStore.getName(), model, reads, writes, diskStore.getSize()));
        }
    }

}
