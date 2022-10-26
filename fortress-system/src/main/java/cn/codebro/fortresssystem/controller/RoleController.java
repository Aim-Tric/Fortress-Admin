package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.Role;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IService<Role> roleService;

    public RoleController(IService<Role> roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/p/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        return Result.success(roleService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize)));
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        Role role = new Role();
        role.setId(id);
        return Result.success(roleService.getOne(new QueryWrapper<>(role)));
    }

    @PostMapping
    public Result add(@RequestBody Role role) {
        roleService.save(role);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Role role) {
        roleService.updateById(role);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        Role role = new Role();
        role.setId(id);
        roleService.removeById(role);
        return Result.success();
    }
}
