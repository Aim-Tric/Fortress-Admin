package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.FortressSysRole;
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
@RequestMapping("/role")
public class RoleController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IService<FortressSysRole> roleService;

    public RoleController(IService<FortressSysRole> roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/p/{page}/{pageSize}")
    public Result page(@PathVariable int page, @PathVariable(required = false) int pageSize) {
        return Result.success(roleService.page(new Page<>(page, pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize)));
    }

    @GetMapping
    public Result getById(String id) {
        FortressSysRole role = new FortressSysRole();
        role.setId(id);
        return Result.success(roleService.getOne(new QueryWrapper<>(role)));
    }

    @PostMapping
    public Result add(FortressSysRole role) {
        roleService.save(role);
        return Result.success();
    }

    @PutMapping
    public Result update(FortressSysRole role) {
        roleService.updateById(role);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(String id) {
        FortressSysRole role = new FortressSysRole();
        role.setId(id);
        roleService.removeById(role);
        return Result.success();
    }
}
