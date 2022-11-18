package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.tree.Treetifier;
import cn.codebro.fortresscommon.tree.Treetify;
import cn.codebro.fortresssystem.mapper.FortressMenuMapper;
import cn.codebro.fortresssystem.pojo.Menu;
import cn.codebro.fortresssystem.service.IMenuService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-02 17:36:27
 */
@Service
public class MenuServiceImpl extends ServiceImpl<FortressMenuMapper, Menu> implements IMenuService {

    @Override
    public List<Treetify<String, Menu>> getAllAsTree() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>(new Menu());
        wrapper.orderByAsc("order_num");
        List<Menu> menus = baseMapper.selectList(wrapper);
        return Treetifier.listToTree(menus);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindRole(String roleId, List<Menu> menus) {
        List<Menu> boundedMenus = baseMapper.getBoundedMenusByRoleId(roleId);
        List<Menu> addMenus = menusFilter(boundedMenus, menus);
        for (Menu menu : addMenus) {
            baseMapper.insertRoleMenu(IdUtil.fastSimpleUUID(), roleId, menu.getId());
        }
        List<Menu> removeMenus = menusFilter(menus, boundedMenus);
        for (Menu menu : removeMenus) {
            baseMapper.removeRoleMenu(roleId, menu.getId());
        }
    }

    private List<Menu> menusFilter(List<Menu> compare1, List<Menu> compare2) {
        if (compare1.size() == 0) {
            return compare2;
        }
        List<Menu> notExistInCompare1 = new ArrayList<>();
        for (Menu role : compare1) {
            for (Menu ownedRole : compare2) {
                if (!StrUtil.equals(role.getId(), ownedRole.getId())) {
                    notExistInCompare1.add(ownedRole);
                }
            }
        }
        return notExistInCompare1;
    }
}
