package cn.codebro.fortresssystem.service;

import cn.codebro.fortresssystem.pojo.User;
import cn.codebro.fortresssystem.pojo.dto.UserDTO;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-22 19:51:39
 */
public interface IAccountService {

    void login(String username, String password, String type);

    void register(UserDTO user);

    User getLoginUser();

    boolean isLogin();
}
