package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.controller.param.ChangePasswordParam;
import cn.codebro.fortresssystem.controller.param.UserInfoParam;
import cn.codebro.fortresssystem.persistence.po.UserPO;
import cn.codebro.fortresssystem.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/p/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        Page<UserPO> result = userService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize));
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        return Result.success(userService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody UserInfoParam user) {
        userService.save(user);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody UserInfoParam user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        userService.removeById(id);
        return Result.success();
    }

}
