package cn.codebro.fortress.system.service.impl;

import cn.codebro.fortress.system.persistence.mapper.RoleMapper;
import cn.codebro.fortress.system.persistence.po.FAuthPO;
import cn.codebro.fortress.system.persistence.po.FMenuPO;
import cn.codebro.fortress.system.persistence.po.FRolePO;
import cn.codebro.fortress.system.persistence.repo.RoleRepo;
import cn.codebro.fortress.system.persistence.repo.UserRoleRepo;
import cn.codebro.fortress.system.pojo.Menu;
import cn.codebro.fortress.system.pojo.Role;
import cn.codebro.fortress.system.controller.param.BindRoleParam;
import cn.codebro.fortress.system.service.IAuthService;
import cn.codebro.fortress.system.service.IMenuService;
import cn.codebro.fortress.system.service.IRoleService;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private IMenuService menuService;
    @Resource
    private IAuthService authService;

    @Resource
    private RoleRepo roleRepo;
    @Resource
    private UserRoleRepo userRoleRepo;


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
        FRolePO role = roleRepo.selectById(bindRoleParam.getId());
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
        List<FAuthPO> auths = new ArrayList<>();
        if (authStringList.size() > 0) {
            auths = authService.getInIds(authStringList);
            if (auths.size() != authStringList.size()) {
                throw new RuntimeException("操作的权限异常！");
            }
        }
        String roleId = bindRoleParam.getId();

        FRolePO role = roleRepo.selectById(roleId);
        if (role == null) {
            throw new RuntimeException("操作的角色不存在！");
        }
        authService.removeAuthByRoleId(roleId);
        authService.saveAuthByRoleId(roleId, auths);
    }

    @Override
    public List<FAuthPO> getRoleWithAuths(String id) {
        return roleRepo.selectAuthsByRoleId(id);
    }

    @Override
    public List<FMenuPO> getRoleWithMenus(String id) {
        return roleRepo.selectMenusByRoleId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertRoleByUserId(String userId, List<String> roles) {
        userRoleRepo.insertByUserId(userId, roles);
    }

}
