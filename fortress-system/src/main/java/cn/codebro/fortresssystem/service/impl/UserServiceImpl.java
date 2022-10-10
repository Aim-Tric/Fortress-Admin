package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresssystem.mapper.FortressUserMapper;
import cn.codebro.fortresssystem.pojo.FortressSysUser;
import cn.codebro.fortresssystem.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class UserServiceImpl extends ServiceImpl<FortressUserMapper, FortressSysUser> implements IUserService {
}
