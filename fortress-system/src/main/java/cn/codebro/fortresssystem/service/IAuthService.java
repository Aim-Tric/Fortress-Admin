package cn.codebro.fortresssystem.service;

import cn.codebro.fortresscommon.tree.Treetify;
import cn.codebro.fortresssystem.pojo.Auth;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public interface IAuthService extends IService<Auth> {
    /**
     * 获取所有权限，并转换成树形
     *
     * @return 树形结构的权限列表
     */
    List<Treetify<String, Auth>> getAllAsTree();

    void removeAuthByRoleId(String roleId);

    void saveAuthByRoleId(String roleId, List<Auth> auths);
}
