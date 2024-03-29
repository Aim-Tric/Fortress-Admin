package cn.codebro.fortress.system.persistence.mapper;

import cn.codebro.fortress.system.pojo.Auth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Mapper
public interface AuthMapper extends BaseMapper<Auth> {

    int countDistributionInRole(Serializable id);

    void insertRoleAuth(@Param("id") String id, @Param("roleId") String roleId, @Param("authId") String authId);

    void deleteAuthByRoleId(String roleId);
}
