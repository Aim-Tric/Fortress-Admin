package cn.codebro.fortress.system.controller;

import cn.codebro.fortress.common.model.Result;
import cn.codebro.fortress.system.persistence.po.FAuthPO;
import cn.codebro.fortress.system.pojo.Auth;
import cn.codebro.fortress.system.service.IAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/tree")
    public Result getAllAsTree() {
        return Result.success(authService.getAllAsTree());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        return Result.success(authService.getById(id));
    }

    @PostMapping
    public Result add(@RequestBody FAuthPO fAuthPO) {
        authService.save(fAuthPO);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody FAuthPO fAuthPO) {
        authService.update(fAuthPO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        authService.removeById(id);
        return Result.success();
    }

}
