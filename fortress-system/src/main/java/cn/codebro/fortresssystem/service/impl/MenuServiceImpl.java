package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.Treetifier;
import cn.codebro.fortresscommon.Treetify;
import cn.codebro.fortresssystem.mapper.FortressMenuMapper;
import cn.codebro.fortresssystem.pojo.Menu;
import cn.codebro.fortresssystem.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
        List<Menu> menus = baseMapper.selectList(new QueryWrapper<>(new Menu()));
        return Treetifier.listToTree(menus);
    }

}
