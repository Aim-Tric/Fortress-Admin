package cn.codebro.fortress.system.controller;

import cn.codebro.fortress.common.model.Result;
import cn.codebro.fortress.system.pojo.Role;
import cn.codebro.fortress.system.controller.param.BindRoleParam;
import cn.codebro.fortress.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/p/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        return Result.success(roleService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize)));
    }

    @GetMapping("/all")
    public Result all() {
        return Result.success(roleService.list());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        return Result.success(roleService.getById(id));
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
        roleService.removeById(id);
        return Result.success();
    }

    @GetMapping("/menu/{id}")
    public Result getRoleMenu(@PathVariable String id) {
        return Result.success(roleService.getRoleWithMenus(id));
    }

    @PostMapping("/menu")
    public Result bindMenu(@RequestBody BindRoleParam bindRoleParam) {
        roleService.bindRoleMenu(bindRoleParam);
        return Result.success();
    }

    @GetMapping("/auth/{id}")
    public Result getRoleAuth(@PathVariable String id) {
        return Result.success(roleService.getRoleWithAuths(id));
    }

    @PostMapping("/auth")
    public Result bindAuth(@RequestBody BindRoleParam bindRoleParam) {
        roleService.bindRoleAuth(bindRoleParam);
        return Result.success();
    }
}
