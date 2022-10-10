package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.FortressSysUser;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IService<FortressSysUser> userService;

    public UserController(IService<FortressSysUser> userService) {
        this.userService = userService;
    }

    @GetMapping("/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        return Result.success(userService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize)));
    }

    @PostMapping
    public Result add(FortressSysUser user) {
        userService.save(user);
        return Result.success();
    }

    @PutMapping
    public Result update(FortressSysUser user) {
        UpdateWrapper<FortressSysUser> wrapper = new UpdateWrapper<>(user);
        wrapper.eq("id", user.getId());
        userService.update(user, wrapper);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(String id) {
        FortressSysUser user = new FortressSysUser();
        user.setId(id);
        userService.removeById(user);
        return Result.success();
    }
}
