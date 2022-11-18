package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.pojo.Menu;
import cn.codebro.fortresssystem.pojo.dto.RoleDTO;
import cn.codebro.fortresssystem.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @menuor Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final IMenuService menuService;

    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/tree")
    public Result getAllAsTree() {
        return Result.success(menuService.getAllAsTree());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        Menu menu = new Menu();
        menu.setId(id);
        return Result.success(menuService.getOne(new QueryWrapper<>(menu)));
    }

    @PostMapping
    public Result add(@RequestBody Menu menu) {
        menuService.save(menu);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        menuService.removeById(id);
        return Result.success();
    }

}
