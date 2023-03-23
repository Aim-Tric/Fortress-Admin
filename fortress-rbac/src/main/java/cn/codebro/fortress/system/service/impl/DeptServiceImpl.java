package cn.codebro.fortress.system.service.impl;

import cn.codebro.fortress.system.persistence.mapper.DeptMapper;
import cn.codebro.fortress.system.pojo.Dept;
import cn.codebro.fortress.system.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
}
