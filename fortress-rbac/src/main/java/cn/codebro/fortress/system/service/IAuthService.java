package cn.codebro.fortress.system.service;

import cn.codebro.fortress.system.persistence.po.FAuthPO;
import org.sagacity.sqltoy.service.SqlToyCRUDService;

import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public interface IAuthService extends SqlToyCRUDService {

    FAuthPO getById(String id);

    boolean removeById(String id);

    /**
     * 获取所有权限，并转换成树形
     *
     * @return 树形结构的权限列表
     */
    List<FAuthPO> getAllAsTree();

    List<FAuthPO> getInIds(List<String> ids);

    void removeAuthByRoleId(String roleId);

    void saveAuthByRoleId(String roleId, List<FAuthPO> auths);

}
