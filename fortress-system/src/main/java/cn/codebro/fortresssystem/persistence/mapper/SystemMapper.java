package cn.codebro.fortresssystem.persistence.mapper;

import cn.codebro.fortresssystem.pojo.SystemInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-21 17:23:08
 */
@Mapper
public interface SystemMapper extends BaseMapper<SystemInfo> {
}
