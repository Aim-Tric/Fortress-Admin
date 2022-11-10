package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.tree.Treetifier;
import cn.codebro.fortresscommon.tree.Treetify;
import cn.codebro.fortresscommon.exception.IllegalBusinessOperationException;
import cn.codebro.fortresssystem.mapper.FortressAuthMapper;
import cn.codebro.fortresssystem.pojo.Auth;
import cn.codebro.fortresssystem.service.IAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class AuthServiceImpl extends ServiceImpl<FortressAuthMapper, Auth> implements IAuthService {

    @Override
    public List<Treetify<String, Auth>> getAllAsTree() {
        List<Auth> auths = baseMapper.selectList(new QueryWrapper<>(new Auth()));
        return Treetifier.listToTree(auths);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        int distributionCount = baseMapper.countDistributionInRole(id);
        if (distributionCount > 0) {
            throw IllegalBusinessOperationException.dump("权限已经被分配了，不能执行删除操作！");
        }
        UpdateWrapper<Auth> wrapper = new UpdateWrapper<>(new Auth());
        wrapper.eq("parent", id);
        baseMapper.delete(wrapper);
        return super.removeById(id);
    }

    @Override
    public boolean removeById(Auth entity) {
        return removeById(entity.getId());
    }
}
