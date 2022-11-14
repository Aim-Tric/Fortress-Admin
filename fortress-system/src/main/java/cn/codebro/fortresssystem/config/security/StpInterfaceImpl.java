package cn.codebro.fortresssystem.config.security;

import cn.codebro.fortresssystem.pojo.Auth;
import cn.codebro.fortresssystem.pojo.Role;
import cn.codebro.fortresssystem.pojo.User;
import cn.codebro.fortresssystem.pojo.dto.UserDTO;
import cn.codebro.fortresssystem.service.IAccountService;
import cn.codebro.fortresssystem.service.IUserService;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-14 09:38:38
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    private final IAccountService accountService;

    @Autowired
    public StpInterfaceImpl(IAccountService userService) {
        this.accountService = userService;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        User user = accountService.getLoginUser();
        Role role = user.getRole();
        List<String> permissionList = new ArrayList<>();
        List<Auth> auths = role.getAuths();
        for (Auth auth : auths) {
            permissionList.add(auth.getIdentify());
        }
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = accountService.getLoginUser();
        List<String> roleNameList = new ArrayList<>();
        Role role = user.getRole();
        roleNameList.add(role.getIdentify());
        return roleNameList;
    }
}
