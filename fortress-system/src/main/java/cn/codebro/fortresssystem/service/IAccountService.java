package cn.codebro.fortresssystem.service;

import cn.codebro.fortresssystem.pojo.User;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-22 19:51:39
 */
public interface IAccountService {

    void login(String username, String password, Integer type);

    void register(User user);

}
