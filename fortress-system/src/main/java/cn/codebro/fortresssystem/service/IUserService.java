package cn.codebro.fortresssystem.service;

import cn.codebro.fortresssystem.pojo.User;
import cn.codebro.fortresssystem.pojo.dto.UserDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public interface IUserService extends IService<UserDTO> {
    User findById(String id);
}
