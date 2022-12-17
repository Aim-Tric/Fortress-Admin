package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.exception.IllegalBusinessOperationException;
import cn.codebro.fortresscommon.exception.IncorrectUsernameOrPasswordException;
import cn.codebro.fortresscommon.exception.UnknownUserException;
import cn.codebro.fortresscommon.exception.UserExistException;
import cn.codebro.fortresssystem.persistence.mapper.FortressUserMapper;
import cn.codebro.fortresssystem.pojo.Role;
import cn.codebro.fortresssystem.pojo.User;
import cn.codebro.fortresssystem.pojo.dto.UserDTO;
import cn.codebro.fortresssystem.service.IAccountService;
import cn.codebro.fortresssystem.service.IRoleService;
import cn.codebro.fortresssystem.service.IUserService;
import cn.dev33.satoken.stp.StpUtil;
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
public class UserServiceImpl extends ServiceImpl<FortressUserMapper, UserDTO> implements IUserService, IAccountService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public final IRoleService roleService;

    @Autowired
    public UserServiceImpl(IRoleService roleService) {
        this.roleService = roleService;
    }

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(UserDTO entity) {
        boolean updated = super.updateById(entity);
        List<String> roleStringList = entity.getRoles();
        List<Role> roles = checkAndGetRoles(roleStringList);
        User user = baseMapper.selectFullUserInfo(entity.getId());
        if (user == null) {
            throw new RuntimeException("操作的角色不存在！");
        }
        roleService.removeRoleByUserId(user.getId());
        roleService.insertRoleByUserId(user.getId(), roles);
        return updated;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(UserDTO entity) {
        boolean saved = super.save(entity);
        List<String> roleStringList = entity.getRoles();
        List<Role> roles = checkAndGetRoles(roleStringList);
        if (saved && roles != null && roles.size() > 0) {
            roleService.insertRoleByUserId(entity.getId(), roles);
        }
        return saved;
    }

    @Override
    public User findById(String id) {
        return baseMapper.selectFullUserInfo(id);
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

    private List<Role> rolesFilter(List<Role> compare1, List<Role> compare2) {
        if (compare1.size() == 0) {
            return compare2;
        }
        List<Role> notExistInCompare1 = new ArrayList<>();
        for (Role role : compare1) {
            for (Role ownedRole : compare2) {
                if (!StrUtil.equals(role.getId(), ownedRole.getId())) {
                    notExistInCompare1.add(ownedRole);
                }
            }
        }
        return notExistInCompare1;
    }
}
