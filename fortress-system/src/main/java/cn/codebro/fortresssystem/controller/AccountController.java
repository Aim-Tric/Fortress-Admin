package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.LoginUser;
import cn.codebro.fortresssystem.service.IAccountService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
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

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginUser loginUser) {
        accountService.login(loginUser.getAccount(), loginUser.getPassword(), loginUser.getType());
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
