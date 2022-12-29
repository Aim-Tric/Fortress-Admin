package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-29 13:38:23
 */
@RestController
@RequestMapping("/doc")
public class DocumentController {
    @PostMapping
    public Result upload() {

        return Result.success();
    }
}
