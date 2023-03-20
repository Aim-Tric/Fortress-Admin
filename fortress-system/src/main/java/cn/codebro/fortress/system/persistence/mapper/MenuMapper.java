package cn.codebro.fortress.system.persistence.mapper;

import cn.codebro.fortress.system.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-02 17:35:28
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    void insertRoleMenu(@Param("id") String id, @Param("roleId") String roleId, @Param("menuId") String menuId);

    void deleteByRoleId(String roleId);

    List<Menu> selectMenuByRoleId(String roleId);

}
