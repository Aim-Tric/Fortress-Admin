package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.exception.IncorrectUsernameOrPasswordException;
import cn.codebro.fortresscommon.exception.UnknownUserException;
import cn.codebro.fortresscommon.exception.UserExistException;
import cn.codebro.fortresssystem.mapper.FortressUserMapper;
import cn.codebro.fortresssystem.pojo.User;
import cn.codebro.fortresssystem.service.IAccountService;
import cn.codebro.fortresssystem.service.IUserService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.EncryptUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class UserServiceImpl extends ServiceImpl<FortressUserMapper, User> implements IUserService, IAccountService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void login(String account, String password, Integer type) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", account);
        User user = baseMapper.selectOne(query);
        if (ObjectUtil.isNull(user)) {
            throw new UnknownUserException(account);
        }
        if (!StrUtil.equals(EncryptUtils.md5Base64(password), user.getPassword())) {
            throw new IncorrectUsernameOrPasswordException(account);
        }
        StpUtil.login(user.getId());
    }

    @Override
    public void register(User registerUser) {
        // TODO 检查用户名是否合法

        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", registerUser.getUsername());
        User queryUser = baseMapper.selectOne(query);
        if (ObjectUtil.isNotNull(queryUser)) {
            throw new UserExistException(registerUser.getUsername());
        }
        registerUser.setPassword(EncryptUtils.md5Base64(registerUser.getPassword()));
        baseMapper.insert(registerUser);
    }
}
