package cn.codebro.fortress.system.service.impl;

import cn.codebro.fortress.system.persistence.mapper.PostMapper;
import cn.codebro.fortress.system.pojo.Post;
import cn.codebro.fortress.system.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {
}
