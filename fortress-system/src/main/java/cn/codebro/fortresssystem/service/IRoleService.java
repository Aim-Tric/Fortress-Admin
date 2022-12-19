package cn.codebro.fortresssystem.service;

import cn.codebro.fortresssystem.pojo.Role;
import cn.codebro.fortresssystem.controller.param.BindRoleParam;
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

    Role getRoleAuth(String id);

    Role getRoleMenu(String id);

    void insertRoleByUserId(String id, List<Role> roles);
}
