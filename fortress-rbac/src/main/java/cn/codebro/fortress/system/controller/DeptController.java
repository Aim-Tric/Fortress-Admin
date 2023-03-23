package cn.codebro.fortress.system.controller;

import cn.codebro.fortress.common.model.Result;
import cn.codebro.fortress.system.pojo.Dept;
import cn.codebro.fortress.system.service.IDeptService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IDeptService deptService;

    public DeptController(IDeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping("/p/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        return Result.success(deptService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize)));
    }

    @GetMapping
    public Result getById(String id) {
        return Result.success(deptService.getById(id));
    }

    @PostMapping
    public Result add(Dept dept) {
        deptService.save(dept);
        return Result.success();
    }

    @PutMapping
    public Result update(Dept dept) {
        deptService.updateById(dept);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(String id) {
        deptService.removeById(id);
        return Result.success();
    }
}
