package cn.codebro.fortress.system.persistence.repo;

import cn.codebro.fortress.system.persistence.po.FAuthPO;
import cn.codebro.fortress.system.persistence.po.FMenuPO;
import cn.codebro.fortress.system.persistence.po.FRolePO;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.MapKit;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-03-27 17:19:10
 */
@Repository
public class RoleRepo {

    @Resource
    private SqlToyLazyDao sqlToyLazyDao;

    public List<FRolePO> selectInIds(List<String> roleIds) {
        return sqlToyLazyDao.findBySql("role_select_in_ids",
                MapKit.keys("roleIds").values(roleIds), FRolePO.class);
    }

    public FRolePO selectById(String id) {
        FRolePO entity = new FRolePO();
        entity.setId(id);
        return sqlToyLazyDao.load(entity);
    }

    public List<FAuthPO> selectAuthsByRoleId(String id) {
        return sqlToyLazyDao.findBySql("role_auth_select_auths_by_role_id",
                MapKit.keys("roleId").values(id), FAuthPO.class);
    }


    public List<FMenuPO> selectMenusByRoleId(String id) {
        return sqlToyLazyDao.findBySql("role_auth_select_auths_by_role_id",
                MapKit.keys("roleId").values(id), FMenuPO.class);
    }
}
