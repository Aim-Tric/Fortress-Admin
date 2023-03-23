package cn.codebro.fortress.system.config.security;

import cn.codebro.fortress.system.pojo.Auth;
import cn.codebro.fortress.system.pojo.Role;
import cn.codebro.fortress.system.pojo.User;
import cn.codebro.fortress.system.service.IAccountService;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Role> roles = user.getRoles();
        Set<String> permissionSet = new HashSet<>();
        for (Role role : roles) {
            List<Auth> auths = role.getAuths();
            for (Auth auth : auths) {
                permissionSet.add(auth.getIdentify());
            }
        }
        return new ArrayList<>(permissionSet);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = accountService.getLoginUser();
        List<String> roleNameList = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            roleNameList.add(role.getIdentify());
        }
        return roleNameList;
    }
}
