package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresssystem.mapper.FortressRoleMapper;
import cn.codebro.fortresssystem.pojo.Menu;
import cn.codebro.fortresssystem.pojo.Role;
import cn.codebro.fortresssystem.pojo.dto.RoleDTO;
import cn.codebro.fortresssystem.service.IMenuService;
import cn.codebro.fortresssystem.service.IRoleService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
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
public class RoleServiceImpl extends ServiceImpl<FortressRoleMapper, Role> implements IRoleService {
    private final IMenuService menuService;

    @Autowired
    public RoleServiceImpl(IMenuService menuService) {
        this.menuService = menuService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserRole(String userId, List<Role> roles) {
        for (Role role : roles) {
            baseMapper.insertUserRole(IdUtil.fastSimpleUUID(), userId, role);
        }
    }

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

}
