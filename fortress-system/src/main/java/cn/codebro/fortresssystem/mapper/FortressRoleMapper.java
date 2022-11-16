package cn.codebro.fortresssystem.mapper;

import cn.codebro.fortresssystem.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Mapper
public interface FortressRoleMapper extends BaseMapper<Role> {
    void insertUserRole(@Param("id") String id, @Param("userId") String userId, @Param("role") Role role);
}
