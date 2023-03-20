package cn.codebro.fortress.system.service;

import cn.codebro.fortress.common.util.tree.Treeable;
import cn.codebro.fortress.system.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-02 17:35:54
 */
public interface IMenuService extends IService<Menu> {
    List<Treeable<String, Menu>> getAllAsTree();

    void deleteMenuByRoleId(String id);

    void saveMenuByRoleId(String id, List<Menu> menus);

    List<Menu> getMenuByRoleId(String roleId);
}
