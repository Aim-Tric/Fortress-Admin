package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.TreeVO;
import cn.codebro.fortresssystem.mapper.FortressAuthMapper;
import cn.codebro.fortresssystem.pojo.Auth;
import cn.codebro.fortresssystem.service.IAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class AuthServiceImpl extends ServiceImpl<FortressAuthMapper, Auth> implements IAuthService {

    @Override
    public TreeVO<Auth> getAllAsTree() {
        return null;
    }
}
