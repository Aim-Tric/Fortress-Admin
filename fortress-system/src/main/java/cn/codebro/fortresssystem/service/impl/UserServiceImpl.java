package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.exception.IllegalBusinessOperationException;
import cn.codebro.fortresscommon.exception.IncorrectUsernameOrPasswordException;
import cn.codebro.fortresscommon.exception.UnknownUserException;
import cn.codebro.fortresscommon.exception.UserExistException;
import cn.codebro.fortresssystem.mapper.FortressUserMapper;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> roleStringList = entity.getRoles();
        boolean updated = super.updateById(entity);
        List<Role> roles = checkAndGetRoles(roleStringList);
        User user = baseMapper.selectFullUserInfo(entity.getId());
        List<Role> ownedRoles = user.getRoles();
        if (updated && roles != null && roles.size() > 0) {
            System.out.println(Arrays.toString(roles.toArray()));
            // 存在于新集合但不存在于旧集合的为新增
            List<Role> addRoles = rolesFilter(ownedRoles, roles);
            roleService.saveUserRole(entity.getId(), addRoles);
            System.out.println(Arrays.toString(addRoles.toArray()));
            // 存在于旧集合但不存在于新集合的为删除
            List<Role> removeRoles = rolesFilter(roles, ownedRoles);
            roleService.removeUserRole(entity.getId(), removeRoles);
            System.out.println(Arrays.toString(removeRoles.toArray()));
        } else if (ownedRoles != null && ownedRoles.size() > 0) {
            // 删除所有角色，意味着账号就无法正常进行系统操作了
            roleService.removeUserRole(entity.getId(), roles);
        }
        return updated;
    }

    @Override
    public boolean save(UserDTO entity) {
        List<String> roleStringList = entity.getRoles();
        List<Role> roles = checkAndGetRoles(roleStringList);
        boolean saved = super.save(entity);
        if (saved && roles != null && roles.size() > 0) {
            roleService.saveUserRole(entity.getId(), roles);
        }
        return saved;
    }

    @Override
    public User findById(String id) {
        return baseMapper.selectFullUserInfo(id);
    }

    private List<Role> checkAndGetRoles(List<String> roleStringList) {
        List<Role> roles = null;
        if (roleStringList != null && roleStringList.size() > 0) {
            QueryWrapper<Role> wrapper = new QueryWrapper<>();
            wrapper.in("id", roleStringList);
            roles = roleService.list(wrapper);
            assert roles.size() == roleStringList.size();
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
