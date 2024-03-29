package cn.codebro.fortress.system.controller;

import cn.codebro.fortress.common.model.Result;
import cn.codebro.fortress.system.pojo.SystemInfo;
import cn.codebro.fortress.system.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-20 20:07:02
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    private final ISystemService systemService;

    @Autowired
    public SystemController(ISystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping
    public Result querySystemConfiguration() {
        return Result.success(systemService.getSystemInfo());
    }

    @PutMapping
    public Result updateSystemConfiguration(@RequestBody SystemInfo systemInfo) {
        systemService.initializeSystem(systemInfo);
        return Result.success();
    }
}
