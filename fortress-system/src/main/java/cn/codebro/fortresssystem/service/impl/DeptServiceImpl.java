package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresssystem.persistence.mapper.DeptMapper;
import cn.codebro.fortresssystem.pojo.Dept;
import cn.codebro.fortresssystem.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
}
