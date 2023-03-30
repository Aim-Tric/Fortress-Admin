package cn.codebro.fortress.system.service.impl;

import cn.codebro.fortress.common.util.tree.TreeUtil;
import cn.codebro.fortress.common.util.SystemBusinessExceptionUtil;
import cn.codebro.fortress.system.persistence.po.FAuthPO;
import cn.codebro.fortress.system.persistence.po.FRoleAuthPO;
import cn.codebro.fortress.system.service.IAuthService;
import cn.hutool.core.util.IdUtil;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.EntityQuery;
import org.sagacity.sqltoy.model.MapKit;
import org.sagacity.sqltoy.model.Page;
import org.sagacity.sqltoy.service.impl.SqlToyCRUDServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class AuthServiceImpl extends SqlToyCRUDServiceImpl implements IAuthService {

    @Resource
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public List<FAuthPO> getAllAsTree() {
        Page<FAuthPO> auths = sqlToyLazyDao.findPageEntity(new Page(10, -1), FAuthPO.class, EntityQuery.create());
        List<FAuthPO> rows = auths.getRows();
        return TreeUtil.listToTree(rows);
    }

    @Override
    public List<FAuthPO> getInIds(List<String> ids) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveAuthByRoleId(String roleId, List<FAuthPO> auths) {
        for (FAuthPO auth : auths) {
            FRoleAuthPO fRoleAuthPO = new FRoleAuthPO();
            fRoleAuthPO.setId(IdUtil.fastSimpleUUID());
            fRoleAuthPO.setRoleId(roleId);
            fRoleAuthPO.setAuthId(auth.getId());
            sqlToyLazyDao.save(fRoleAuthPO);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeAuthByRoleId(String roleId) {
        sqlToyLazyDao.executeSql("auth_delete_auth_by_role_id",
                MapKit.keys("roleId").values(roleId));
    }

    @Override
    public FAuthPO getById(String id) {
        FAuthPO fAuthPO = new FAuthPO();
        fAuthPO.setId(id);
        return sqlToyLazyDao.load(fAuthPO);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(String id) {
        FRoleAuthPO fRoleAuthPO = new FRoleAuthPO();
        fRoleAuthPO.setAuthId(id);
        long distributionCount = sqlToyLazyDao.getCount(FRoleAuthPO.class, EntityQuery.create().where("auth_id=:authId").values(fRoleAuthPO));
        if (distributionCount > 0) {
            throw SystemBusinessExceptionUtil.dump("权限已经被分配了，不能执行删除操作！");
        }
        Long effectRows = sqlToyLazyDao.executeSql("auth_delete_auth_by_auth_id", MapKit.keys("authId").values(id));
        return effectRows > 0;
    }

}
