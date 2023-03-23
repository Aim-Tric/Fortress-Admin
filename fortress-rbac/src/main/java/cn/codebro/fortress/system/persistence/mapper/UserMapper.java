package cn.codebro.fortress.system.persistence.mapper;

import cn.codebro.fortress.system.persistence.po.UserPO;
import cn.codebro.fortress.system.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

    User selectFullUserInfo(String userId);

}
