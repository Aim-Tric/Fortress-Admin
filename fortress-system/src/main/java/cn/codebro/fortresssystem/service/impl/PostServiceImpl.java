package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresssystem.persistence.mapper.PostMapper;
import cn.codebro.fortresssystem.pojo.Post;
import cn.codebro.fortresssystem.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {
}
