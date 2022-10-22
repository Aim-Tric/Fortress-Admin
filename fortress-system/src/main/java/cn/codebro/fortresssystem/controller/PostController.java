package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.FortressSysPost;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/post")
public class PostController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IService<FortressSysPost> postService;

    public PostController(IService<FortressSysPost> postService) {
        this.postService = postService;
    }

    @GetMapping("/p/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        return Result.success(postService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize)));
    }

    @GetMapping
    public Result getById(String id) {
        FortressSysPost post = new FortressSysPost();
        post.setId(id);
        return Result.success(postService.getOne(new QueryWrapper<>(post)));
    }

    @PostMapping
    public Result add(FortressSysPost post) {
        postService.save(post);
        return Result.success();
    }

    @PutMapping
    public Result update(FortressSysPost post) {
        postService.updateById(post);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(String id) {
        FortressSysPost post = new FortressSysPost();
        post.setId(id);
        postService.removeById(post);
        return Result.success();
    }
}
