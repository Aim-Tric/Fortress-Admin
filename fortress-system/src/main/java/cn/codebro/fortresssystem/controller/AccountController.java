package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.LoginParam;
import cn.codebro.fortresssystem.service.IAccountService;
import cn.dev33.satoken.stp.StpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
