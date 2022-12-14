package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresssystem.persistence.mapper.RoleMapper;
import cn.codebro.fortresssystem.pojo.Auth;
import cn.codebro.fortresssystem.pojo.Menu;
import cn.codebro.fortresssystem.pojo.Role;
import cn.codebro.fortresssystem.controller.param.BindRoleParam;
import cn.codebro.fortresssystem.service.IAuthService;
import cn.codebro.fortresssystem.service.IMenuService;
import cn.codebro.fortresssystem.service.IRoleService;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    private final IMenuService menuService;
    private final IAuthService authService;

    @Autowired
    public RoleServiceImpl(IMenuService menuService, IAuthService authService) {
        this.menuService = menuService;
        this.authService = authService;
    }

    @Override
    public void removeRoleByUserId(String userId) {
        baseMapper.deleteRoleByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindRoleMenu(BindRoleParam bindRoleParam) {
        List<String> menuStringList = bindRoleParam.getMenus();
        List<Menu> menus = new ArrayList<>();
        if (menuStringList.size() > 0) {
            QueryWrapper<Menu> wrapper = new QueryWrapper<>();
            wrapper.in("id", menuStringList);
            menus = menuService.list(wrapper);
            if (menus.size() != menuStringList.size()) {
                throw new RuntimeException("操作的菜单异常！");
            }
        }
        Role role = getRoleMenu(bindRoleParam.getId());
        if (role == null) {
            throw new RuntimeException("操作的角色不存在！");
        }
        menuService.deleteMenuByRoleId(role.getId());
        menuService.saveMenuByRoleId(role.getId(), menus);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindRoleAuth(BindRoleParam bindRoleParam) {
        List<String> authStringList = bindRoleParam.getAuths();
        List<Auth> auths = new ArrayList<>();
        if (authStringList.size() > 0) {
            QueryWrapper<Auth> wrapper = new QueryWrapper<>();
            wrapper.in("id", authStringList);
            auths = authService.list(wrapper);
            if (auths.size() != authStringList.size()) {
                throw new RuntimeException("操作的权限异常！");
            }
        }
        String roleId = bindRoleParam.getId();
        Role role = baseMapper.selectRoleAuthByRoleId(roleId);
        if (role == null) {
            throw new RuntimeException("操作的角色不存在！");
        }
        authService.removeAuthByRoleId(roleId);
        authService.saveAuthByRoleId(roleId, auths);
    }

    @Override
    public Role getRoleAuth(String id) {
        return baseMapper.selectRoleAuthByRoleId(id);
    }

    @Override
    public Role getRoleMenu(String id) {
        return baseMapper.selectRoleMenuByRoleId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertRoleByUserId(String userId, List<Role> roles) {
        for (Role role : roles) {
            baseMapper.insertRoleByUserId(IdUtil.fastSimpleUUID(), userId, role.getId());
        }
    }

}
