package cn.codebro.fortress.system.persistence.mapper;

import cn.codebro.fortress.system.persistence.po.FilePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-01-03 13:16:07
 */
@Mapper
public interface FileMapper extends BaseMapper<FilePO> {
}
