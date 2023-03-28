package cn.codebro.fortress.system.persistence.repo;

import cn.codebro.fortress.system.persistence.po.FUserPO;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-03-27 14:04:39
 */
@Repository
public class UserRepo {

    @Resource
    private SqlToyLazyDao sqlToyLazyDao;

    public FUserPO selectByUsername(String username) {
        FUserPO entity = new FUserPO();
        entity.setUsername(username);
        return sqlToyLazyDao.load(entity);
    }

    public boolean insert(FUserPO fUserPO) {
        return sqlToyLazyDao.save(fUserPO) != null;
    }

    public boolean update(FUserPO fUserPO) {
        return sqlToyLazyDao.update(fUserPO) > 0;
    }


}
