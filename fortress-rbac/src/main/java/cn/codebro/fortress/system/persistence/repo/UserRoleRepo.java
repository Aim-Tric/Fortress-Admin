package cn.codebro.fortress.system.persistence.repo;

import cn.codebro.fortress.system.persistence.po.FRolePO;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-03-28 18:41:29
 */
@Repository
public class UserRoleRepo {

    @Resource
    private SqlToyLazyDao sqlToyLazyDao;

    public List<FRolePO> selectByUserId(String userId) {

        return null;
    }

    public boolean insertByUserId(String userId, List<String> roles) {

        return false;
    }

    public boolean deleteByUserId(String userId) {

        return false;
    }

}
