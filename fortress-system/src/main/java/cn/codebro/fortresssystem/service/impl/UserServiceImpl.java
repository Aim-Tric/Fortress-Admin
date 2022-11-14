package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.exception.IllegalBusinessOperationException;
import cn.codebro.fortresscommon.exception.IncorrectUsernameOrPasswordException;
import cn.codebro.fortresscommon.exception.UnknownUserException;
import cn.codebro.fortresscommon.exception.UserExistException;
import cn.codebro.fortresssystem.config.system.SystemProperty;
import cn.codebro.fortresssystem.mapper.FortressUserMapper;
import cn.codebro.fortresssystem.pojo.User;
import cn.codebro.fortresssystem.pojo.dto.UserDTO;
import cn.codebro.fortresssystem.service.IAccountService;
import cn.codebro.fortresssystem.service.IRoleService;
import cn.codebro.fortresssystem.service.IUserService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.EncryptUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class UserServiceImpl extends ServiceImpl<FortressUserMapper, UserDTO> implements IUserService, IAccountService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void login(String account, String password, String type) {
        if (!StrUtil.equals("account", type) && !StrUtil.equals("phone", type)) {
            throw IllegalBusinessOperationException.dump("不支持的登录类型：" + type);
        } else {
            String column;
            if (StrUtil.equals("account", type)) {
                column = "username";
            } else if (StrUtil.equals("phone", type)) {
                column = "phone";
            } else {
                throw IllegalBusinessOperationException.dump("不支持的登录类型：" + type);
            }
            QueryWrapper<UserDTO> query = new QueryWrapper<>();
            query.eq(column, account);
            UserDTO user = baseMapper.selectOne(query);
            if (ObjectUtil.isNull(user)) {
                throw new UnknownUserException(account);
            }
            if (!StrUtil.equals(password, user.getPassword())) {
                throw new IncorrectUsernameOrPasswordException(account);
            }
            StpUtil.login(user.getId());
        }
    }

    @Override
    public void register(UserDTO registerUser) {
        QueryWrapper<UserDTO> query = new QueryWrapper<>();
        query.eq("username", registerUser.getUsername());
        UserDTO queryUser = baseMapper.selectOne(query);
        if (ObjectUtil.isNotNull(queryUser)) {
            throw new UserExistException(registerUser.getUsername());
        }
        baseMapper.insert(registerUser);
    }

    @Override
    public User getLoginUser() {
        String loginId = (String) StpUtil.getLoginId();
        return baseMapper.selectFullUserInfo(loginId);
    }

    @Override
    public boolean isLogin() {
        return StpUtil.isLogin();
    }

}
