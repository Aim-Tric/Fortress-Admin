package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresssystem.mapper.FortressRoleMapper;
import cn.codebro.fortresssystem.pojo.Auth;
import cn.codebro.fortresssystem.pojo.Menu;
import cn.codebro.fortresssystem.pojo.Role;
import cn.codebro.fortresssystem.pojo.dto.RoleDTO;
import cn.codebro.fortresssystem.service.IAuthService;
import cn.codebro.fortresssystem.service.IMenuService;
import cn.codebro.fortresssystem.service.IRoleService;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class RoleServiceImpl extends ServiceImpl<FortressRoleMapper, Role> implements IRoleService {
    private final IMenuService menuService;
    private final IAuthService authService;

    @Autowired
    public RoleServiceImpl(IMenuService menuService, IAuthService authService) {
        this.menuService = menuService;
        this.authService = authService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserRole(String userId, List<Role> roles) {
        for (Role role : roles) {
            baseMapper.insertUserRole(IdUtil.fastSimpleUUID(), userId, role);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeUserRole(String userId, List<Role> roles) {
        for (Role role : roles) {
            baseMapper.deleteUserRole(userId, role);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindRoleMenu(RoleDTO roleDTO) {
        List<String> menuStringList = roleDTO.getMenus();
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.in("id", menuStringList);
        List<Menu> menus = menuService.list(wrapper);
        menuService.bindRole(roleDTO.getId(), menus);
    }

    @Override
    public void bindRoleAuth(RoleDTO roleDTO) {
        List<String> authStringList = roleDTO.getAuths();
        QueryWrapper<Auth> wrapper = new QueryWrapper<>();
        wrapper.in("id", authStringList);
        List<Auth> auths = authService.list(wrapper);
        authService.bindRole(roleDTO.getId(), auths);
    }

    @Override
    public Role getRoleAuth(String id) {
        return baseMapper.selectRoleAuthByRoleId(id);
    }

    @Override
    public Role getRoleMenu(String id) {
        return baseMapper.selectRoleMenuByRoleId(id);
    }


}
