package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-20 20:07:02
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    @GetMapping
    public Result querySystemConfiguration() {
        // 系统设置包括：系统名称、系统状态、系统初始化时间、系统管理员账号、系统管理员密码
        return Result.success();
    }

    @PutMapping
    public Result updateSystemConfiguration() {

        return Result.success();
    }
}
