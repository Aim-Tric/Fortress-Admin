package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.Treetifier;
import cn.codebro.fortresscommon.Treetify;
import cn.codebro.fortresssystem.mapper.FortressAuthMapper;
import cn.codebro.fortresssystem.pojo.Auth;
import cn.codebro.fortresssystem.service.IAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
