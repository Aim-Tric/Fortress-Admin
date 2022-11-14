package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.tree.Treetifier;
import cn.codebro.fortresscommon.tree.Treetify;
import cn.codebro.fortresssystem.mapper.FortressMenuMapper;
import cn.codebro.fortresssystem.pojo.Menu;
import cn.codebro.fortresssystem.service.IMenuService;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        for (Menu menu : menus) {
            baseMapper.insertRoleMenu(IdUtil.fastUUID(), roleId, menu.getId());
        }
    }

}
