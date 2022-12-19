package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.exception.IncorrectUsernameOrPasswordException;
import cn.codebro.fortresscommon.exception.UnknownUserException;
import cn.codebro.fortresscommon.exception.UserExistException;
import cn.codebro.fortresssystem.controller.param.ChangePasswordParam;
import cn.codebro.fortresssystem.controller.param.UserInfoParam;
import cn.codebro.fortresscommon.util.SystemBusinessExceptionUtil;
import cn.codebro.fortresssystem.persistence.mapper.FortressUserMapper;
import cn.codebro.fortresssystem.persistence.po.UserPO;
import cn.codebro.fortresssystem.pojo.Role;
import cn.codebro.fortresssystem.pojo.User;
import cn.codebro.fortresssystem.service.IAccountService;
import cn.codebro.fortresssystem.service.IRoleService;
import cn.codebro.fortresssystem.service.IUserService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class UserServiceImpl extends ServiceImpl<FortressUserMapper, UserPO> implements IUserService, IAccountService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public final IRoleService roleService;

    @Autowired
    public UserServiceImpl(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void login(String account, String password, String type) {
        if (!StrUtil.equals("account", type) && !StrUtil.equals("phone", type)) {
            throw SystemBusinessExceptionUtil.dump("不支持的登录类型：" + type);
        } else {
            String column;
            if (StrUtil.equals("account", type)) {
                column = "username";
            } else if (StrUtil.equals("phone", type)) {
                column = "phone";
            } else {
                throw SystemBusinessExceptionUtil.dump("不支持的登录类型：" + type);
            }
            QueryWrapper<UserPO> query = new QueryWrapper<>();
            query.eq(column, account);
            UserPO user = baseMapper.selectOne(query);
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
    public void register(UserPO registerUser) {
        QueryWrapper<UserPO> query = new QueryWrapper<>();
        query.eq("username", registerUser.getUsername());
        UserPO queryUser = baseMapper.selectOne(query);
        if (ObjectUtil.isNotNull(queryUser)) {
            throw new UserExistException(registerUser.getUsername());
        }
        baseMapper.insert(registerUser);
    }

    @Override
    public void resetPassword(String username, String phone, String smsCode) {

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

    @Override
    public User findById(String id) {
        return baseMapper.selectFullUserInfo(id);
    }

    @Override
    public void changePassword(String id, ChangePasswordParam changePasswordParam) {
        UserPO userPO = baseMapper.selectById(id);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UserInfoParam userInfoParam) {
        UserPO userPO = new UserPO();
        BeanUtil.copyProperties(userInfoParam, userPO);
        boolean saved = save(userPO);
        if (!saved) {
            throw SystemBusinessExceptionUtil.dump("用户信息新增失败!");
        }
        List<String> roleStringList = userInfoParam.getRoles();
        List<Role> roles = checkAndGetRoles(roleStringList);
        if (roles != null && roles.size() > 0) {
            roleService.insertRoleByUserId(userInfoParam.getId(), roles);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UserInfoParam userInfoParam) {
        UserPO userPO = new UserPO();
        BeanUtil.copyProperties(userInfoParam, userPO);
        boolean updated = updateById(userPO);
        if (!updated) {
            throw SystemBusinessExceptionUtil.dump("用户信息更新失败!");
        }
        List<String> roleStringList = userInfoParam.getRoles();
        List<Role> roles = checkAndGetRoles(roleStringList);
        User user = baseMapper.selectFullUserInfo(userInfoParam.getId());
        if (user == null) {
            throw new RuntimeException("操作的角色不存在！");
        }
        roleService.removeRoleByUserId(user.getId());
        roleService.insertRoleByUserId(user.getId(), roles);
    }

    private List<Role> checkAndGetRoles(List<String> roleStringList) {
        List<Role> roles = new ArrayList<>();
        if (roleStringList != null && roleStringList.size() > 0) {
            QueryWrapper<Role> wrapper = new QueryWrapper<>();
            wrapper.in("id", roleStringList);
            roles = roleService.list(wrapper);
            if (roles.size() != roleStringList.size()) {
                throw new RuntimeException("操作的角色异常！");
            }
        }
        return roles;
    }

}
