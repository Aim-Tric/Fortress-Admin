package cn.codebro.fortress.system.controller;

import cn.codebro.fortress.common.model.Result;
import cn.codebro.fortress.system.controller.param.ChangePasswordParam;
import cn.codebro.fortress.system.controller.param.LoginParam;
import cn.codebro.fortress.system.pojo.User;
import cn.codebro.fortress.system.service.IAccountService;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-22 19:34:57
 */
@RestController
public class AccountController {

    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginParam loginParam) {
        accountService.login(loginParam.getAccount(), loginParam.getPassword(), loginParam.getType());
        return Result.success();
    }

    @PostMapping("/register")
    public Result register() {
        return Result.fail();
    }

    @GetMapping("/logout")
    public Result logout() {
        StpUtil.logout();
        return Result.success();
    }

    @GetMapping("/isLogin")
    public Result isLogin() {
        return Result.success(accountService.isLogin());
    }

    @GetMapping("/userInfo")
    public Result userInfo() {
        return Result.success(accountService.getLoginUser());
    }

    @PutMapping("/account")
    public Result changePassword(@RequestBody ChangePasswordParam changePasswordParam) {
        User currentUser = accountService.getLoginUser();
        accountService.changePassword(currentUser, changePasswordParam);
        return Result.success();
    }
}
