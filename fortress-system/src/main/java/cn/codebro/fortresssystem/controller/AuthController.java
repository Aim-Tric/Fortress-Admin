package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.FortressSysAuth;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IService<FortressSysAuth> authService;

    public AuthController(IService<FortressSysAuth> authService) {
        this.authService = authService;
    }

    @GetMapping("/p/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        return Result.success(authService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize)));
    }

    @GetMapping
    public Result getById(String id) {
        FortressSysAuth auth = new FortressSysAuth();
        auth.setId(id);
        return Result.success(authService.getOne(new QueryWrapper<>(auth)));
    }

    @PostMapping
    public Result add(FortressSysAuth auth) {
        authService.save(auth);
        return Result.success();
    }

    @PutMapping
    public Result update(FortressSysAuth auth) {
        authService.updateById(auth);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(String id) {
        FortressSysAuth auth = new FortressSysAuth();
        auth.setId(id);
        authService.removeById(auth);
        return Result.success();
    }
}
