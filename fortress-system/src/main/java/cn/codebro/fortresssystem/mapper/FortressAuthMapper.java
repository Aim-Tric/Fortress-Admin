package cn.codebro.fortresssystem.mapper;

import cn.codebro.fortresssystem.pojo.Auth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Mapper
public interface FortressAuthMapper extends BaseMapper<Auth> {

    int countDistributionInRole(Serializable id);

}
