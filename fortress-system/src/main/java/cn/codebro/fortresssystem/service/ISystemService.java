package cn.codebro.fortresssystem.service;

import cn.codebro.fortresssystem.pojo.SystemInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-21 17:22:09
 */
public interface ISystemService extends IService<SystemInfo> {

    SystemInfo getSystemInfo();

    boolean initialized();

    void preInitializeSystem();

    void initializeSystem(SystemInfo systemInfo);


}
