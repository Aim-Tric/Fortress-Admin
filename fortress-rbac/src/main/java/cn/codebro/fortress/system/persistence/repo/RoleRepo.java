package cn.codebro.fortress.system.persistence.repo;

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

    
}
