package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.SystemInfo;
import cn.codebro.fortresssystem.service.ISystemService;
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
        systemService.updateById(systemInfo);
        return Result.success();
    }
}
