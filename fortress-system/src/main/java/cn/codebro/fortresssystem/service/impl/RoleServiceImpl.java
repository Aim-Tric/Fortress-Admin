package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresssystem.mapper.FortressRoleMapper;
import cn.codebro.fortresssystem.pojo.Role;
import cn.codebro.fortresssystem.service.IRoleService;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class RoleServiceImpl extends ServiceImpl<FortressRoleMapper, Role> implements IRoleService {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserRole(String userId, List<Role> roles) {
        for (Role role : roles) {
            baseMapper.insertUserRole(IdUtil.fastSimpleUUID(), userId, role);
        }
    }

    @Override
    public void removeUserRole(String userId, List<Role> roles) {
        for (Role role : roles) {
            baseMapper.deleteUserRole(userId, role);
        }
    }
}
