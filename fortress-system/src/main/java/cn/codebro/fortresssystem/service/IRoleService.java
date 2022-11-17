package cn.codebro.fortresssystem.service;

import cn.codebro.fortresssystem.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public interface IRoleService extends IService<Role> {
    void saveUserRole(String userId, List<Role> roles);

    void removeUserRole(String userId, List<Role> roles);
}
