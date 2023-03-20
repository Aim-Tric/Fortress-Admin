package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortress.common.util.tree.TreeUtil;
import cn.codebro.fortress.common.util.tree.Treeable;
import cn.codebro.fortresssystem.persistence.mapper.MenuMapper;
import cn.codebro.fortresssystem.pojo.Menu;
import cn.codebro.fortresssystem.service.IMenuService;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-02 17:36:27
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    private final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Override
    public List<Treeable<String, Menu>> getAllAsTree() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>(new Menu());
        wrapper.orderByAsc("order_num");
        List<Menu> menus = baseMapper.selectList(wrapper);
        return TreeUtil.listToTree(menus);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMenuByRoleId(String roleId) {
        baseMapper.deleteByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveMenuByRoleId(String roleId, List<Menu> menus) {
        for (Menu menu : menus) {
            baseMapper.insertRoleMenu(IdUtil.fastSimpleUUID(), roleId, menu.getId());
        }
    }

    @Override
    public List<Menu> getMenuByRoleId(String roleId) {
        return baseMapper.selectMenuByRoleId(roleId);
    }

}
