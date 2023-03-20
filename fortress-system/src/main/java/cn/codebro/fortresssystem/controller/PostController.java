package cn.codebro.fortresssystem.controller;

import cn.codebro.fortress.common.model.Result;
import cn.codebro.fortresssystem.pojo.Post;
import cn.codebro.fortresssystem.service.IPostService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/post")
public class PostController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/p/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        return Result.success(postService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize)));
    }

    @GetMapping
    public Result getById(String id) {
        Post post = new Post();
        post.setId(id);
        return Result.success(postService.getOne(new QueryWrapper<>(post)));
    }

    @PostMapping
    public Result add(Post post) {
        postService.save(post);
        return Result.success();
    }

    @PutMapping
    public Result update(Post post) {
        postService.updateById(post);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(String id) {
        postService.removeById(id);
        return Result.success();
    }
}
