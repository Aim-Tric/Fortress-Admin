package cn.codebro.fortress.system.service;

import cn.codebro.fortress.system.persistence.po.FAuthPO;
import cn.codebro.fortress.system.persistence.po.FMenuPO;
import cn.codebro.fortress.system.pojo.Role;
import cn.codebro.fortress.system.controller.param.BindRoleParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public interface IRoleService extends IService<Role> {

    void removeRoleByUserId(String userId);

    void bindRoleMenu(BindRoleParam bindRoleParam);

    void bindRoleAuth(BindRoleParam bindRoleParam);

    List<FAuthPO> getRoleWithAuths(String id);

    List<FMenuPO> getRoleWithMenus(String id);

    void insertRoleByUserId(String id, List<String> roles);
}
