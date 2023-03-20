package cn.codebro.fortress.system.service;

import cn.codebro.fortress.system.controller.param.UserInfoParam;
import cn.codebro.fortress.system.persistence.po.UserPO;
import cn.codebro.fortress.system.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public interface IUserService extends IService<UserPO> {
    User findById(String id);

    void save(UserInfoParam userInfoParam);

    void update(UserInfoParam userInfoParam);
}
