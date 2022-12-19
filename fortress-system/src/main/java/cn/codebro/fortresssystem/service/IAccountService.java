package cn.codebro.fortresssystem.service;

import cn.codebro.fortresssystem.persistence.po.UserPO;
import cn.codebro.fortresssystem.pojo.User;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-22 19:51:39
 */
public interface IAccountService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @param type     登录类型
     */
    void login(String username, String password, String type);

    /**
     * 用户注册
     *
     * @param user 注册的用户信息
     */
    void register(UserPO user);

    /**
     * 申请重置密码，校验短信验证码
     *
     * @param username 重置密码的用户名
     * @param phone    手机号
     * @param smsCode  短信验证码
     */
    void resetPassword(String username, String phone, String smsCode);

    /**
     * 获取当前登录的用户信息
     *
     * @return 包含角色信息的登录用户信息
     */
    User getLoginUser();

    /**
     * 检查用户是否登录
     *
     * @return 是否登录
     */
    boolean isLogin();
}
