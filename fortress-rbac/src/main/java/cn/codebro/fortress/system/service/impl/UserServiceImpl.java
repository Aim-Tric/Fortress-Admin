package cn.codebro.fortress.system.service.impl;

import cn.codebro.fortress.common.exception.IncorrectUsernameOrPasswordException;
import cn.codebro.fortress.common.exception.UnknownUserException;
import cn.codebro.fortress.common.exception.UserExistException;
import cn.codebro.fortress.system.controller.param.ChangePasswordParam;
import cn.codebro.fortress.system.controller.param.UserInfoParam;
import cn.codebro.fortress.common.util.SystemBusinessExceptionUtil;
import cn.codebro.fortress.system.persistence.mapper.UserMapper;
import cn.codebro.fortress.system.persistence.po.FRolePO;
import cn.codebro.fortress.system.persistence.po.FUserPO;
import cn.codebro.fortress.system.persistence.po.UserPO;
import cn.codebro.fortress.system.persistence.repo.RoleRepo;
import cn.codebro.fortress.system.persistence.repo.UserRepo;
import cn.codebro.fortress.system.persistence.repo.UserRoleRepo;
import cn.codebro.fortress.system.pojo.Role;
import cn.codebro.fortress.system.pojo.User;
import cn.codebro.fortress.system.service.IAccountService;
import cn.codebro.fortress.system.service.IRoleService;
import cn.codebro.fortress.system.service.IUserService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService, IAccountService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private SqlToyLazyDao sqlToyLazyDao;
    @Resource
    private UserRepo userRepo;
    @Resource
    private UserRoleRepo userRoleRepo;
    @Resource
    private RoleRepo roleRepo;
    private final IRoleService roleService;

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
            FUserPO user = userRepo.selectByUsername(account);
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
    public void register(FUserPO registerUser) {
        FUserPO queryUser = userRepo.selectByUsername(registerUser.getUsername());
        if (ObjectUtil.isNotNull(queryUser)) {
            throw new UserExistException(registerUser.getUsername());
        }
        userRepo.insert(registerUser);
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
    public void changePassword(User user, ChangePasswordParam changePasswordParam) {
        user.changePassword(changePasswordParam.getOldPassword(), changePasswordParam.getNewPassword());
        FUserPO userPO = new FUserPO();
        BeanUtil.copyProperties(user, userPO);
        userRepo.update(userPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UserInfoParam userInfoParam) {
        FUserPO userPO = new FUserPO();
        BeanUtil.copyProperties(userInfoParam, userPO);
        boolean saved = userRepo.insert(userPO);
        if (!saved) {
            throw SystemBusinessExceptionUtil.dump("用户信息新增失败!");
        }
        List<String> roleStringList = userInfoParam.getRoles();
        List<FRolePO> roles = checkAndGetRoles(roleStringList);
        String userId = userPO.getId();
        if (roles != null && roles.size() > 0) {
            userRoleRepo.insertByUserId(userId, roleStringList);
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
        checkAndGetRoles(roleStringList);
        User user = baseMapper.selectFullUserInfo(userInfoParam.getId());
        if (user == null) {
            throw new RuntimeException("操作的角色不存在！");
        }
        String userId = user.getId();
        userRoleRepo.deleteByUserId(userId);
        userRoleRepo.insertByUserId(userId, roleStringList);
    }

    private List<FRolePO> checkAndGetRoles(List<String> roleStringList) {
        List<FRolePO> roles = new ArrayList<>();
        if (roleStringList != null && roleStringList.size() > 0) {
            roles = roleRepo.selectInIds(roleStringList);
            if (roles.size() != roleStringList.size()) {
                throw new RuntimeException("操作的角色异常！");
            }
        }
        return roles;
    }

}
