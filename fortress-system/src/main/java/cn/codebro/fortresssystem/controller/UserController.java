package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IService<User> userService;

    public UserController(IService<User> userService) {
        this.userService = userService;
    }

    @GetMapping("/p/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        return Result.success(userService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize)));
    }

    @GetMapping
    public Result getById(String id) {
        User user = new User();
        user.setId(id);
        return Result.success(userService.getOne(new QueryWrapper<>(user)));
    }

    @PostMapping
    public Result add(User user) {
        userService.save(user);
        return Result.success();
    }

    @PutMapping
    public Result update(User user) {
        userService.updateById(user);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(String id) {
        User user = new User();
        user.setId(id);
        userService.removeById(user);
        return Result.success();
    }
}
