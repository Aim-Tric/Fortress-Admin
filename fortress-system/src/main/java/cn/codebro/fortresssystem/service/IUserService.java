package cn.codebro.fortresssystem.service;

import cn.codebro.fortresssystem.controller.param.ChangePasswordParam;
import cn.codebro.fortresssystem.controller.param.UserInfoParam;
import cn.codebro.fortresssystem.persistence.po.UserPO;
import cn.codebro.fortresssystem.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public interface IUserService extends IService<UserPO> {
    User findById(String id);

    void changePassword(String id, ChangePasswordParam changePasswordParam);

    void save(UserInfoParam userInfoParam);

    void update(UserInfoParam userInfoParam);
}
