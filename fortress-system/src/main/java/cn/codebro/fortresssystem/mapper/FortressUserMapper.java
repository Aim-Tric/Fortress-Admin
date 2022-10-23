package cn.codebro.fortresssystem.mapper;

import cn.codebro.fortresssystem.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Mapper
public interface FortressUserMapper extends BaseMapper<User> {
}
