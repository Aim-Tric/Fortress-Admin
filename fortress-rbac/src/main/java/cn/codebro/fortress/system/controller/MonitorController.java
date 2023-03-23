package cn.codebro.fortress.system.controller;

import cn.codebro.fortress.common.model.Result;
import cn.codebro.fortress.system.pojo.monitor.ServerInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-26 18:44:11
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @GetMapping
    public Result getServerInfo() {
        return Result.success(ServerInfo.getInstance());
    }


}
