package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.LoginUser;
import cn.codebro.fortresssystem.pojo.User;
import cn.codebro.fortresssystem.service.IAccountService;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Result login(LoginUser loginUser) {
        if (StrUtil.isBlank(loginUser.getAccount())) {
            logger.error("登录用户名为空！");
        }
        if (StrUtil.isBlank(loginUser.getPassword())) {
            logger.error("登录密码为空！");
        }
        if (StrUtil.isBlank(loginUser.getValidateCode())) {
            logger.error("验证码为空！");
        }
        accountService.login(loginUser.getAccount(), loginUser.getPassword(), loginUser.getType());
        return Result.success();
    }

    @PostMapping("/register")
    public Result register() {

        return Result.success();
    }
}
