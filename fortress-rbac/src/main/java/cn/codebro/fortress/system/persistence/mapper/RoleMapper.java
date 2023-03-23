package cn.codebro.fortress.system.persistence.mapper;

import cn.codebro.fortress.system.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    void insertRoleByUserId(@Param("id") String id, @Param("userId") String userId, @Param("roleId") String roleId);

    Role selectRoleAuthByRoleId(String id);

    Role selectRoleMenuByRoleId(String id);

    void deleteRoleByUserId(String userId);
}
