package cn.codebro.fortresssystem.service;

import cn.codebro.fortresscommon.tree.Treetify;
import cn.codebro.fortresssystem.pojo.Menu;
import cn.codebro.fortresssystem.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-02 17:35:54
 */
public interface IMenuService extends IService<Menu> {
    List<Treetify<String, Menu>> getAllAsTree();

    void deleteMenuByRoleId(String id);

    void saveMenuByRoleId(String id, List<Menu> menus);

    List<Menu> getMenuByRoleId(String roleId);
}
