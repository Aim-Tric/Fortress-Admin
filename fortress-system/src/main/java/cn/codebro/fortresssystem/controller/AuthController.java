package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.Auth;
import cn.codebro.fortresssystem.service.IAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Auth auth = new Auth();
        auth.setId(id);
        return Result.success(authService.getOne(new QueryWrapper<>(auth)));
    }

    @PostMapping
    public Result add(@RequestBody Auth auth) {
        authService.save(auth);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Auth auth) {
        authService.updateById(auth);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        Auth auth = new Auth();
        auth.setId(id);
        authService.removeById(auth);
        return Result.success();
    }

    @PostMapping("/{roleId}")
    public Result bindRole(@RequestBody List<Auth> auths, @PathVariable String roleId) {
        authService.bindRole(roleId, auths);
        return Result.success();
    }
}
