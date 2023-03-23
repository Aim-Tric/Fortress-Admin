package cn.codebro.fortress.system.controller;

import cn.codebro.fortress.common.model.Result;
import cn.codebro.fortress.common.util.tree.TreeUtil;
import cn.codebro.fortress.system.pojo.Menu;
import cn.codebro.fortress.system.pojo.Role;
import cn.codebro.fortress.system.pojo.User;
import cn.codebro.fortress.system.service.IAccountService;
import cn.codebro.fortress.system.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @menuor Guo wentao
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final IMenuService menuService;
    private final IAccountService accountService;

    public MenuController(IMenuService menuService, IAccountService accountService) {
        this.menuService = menuService;
        this.accountService = accountService;
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

    @GetMapping("/user")
    public Result getUserMenus() {
        User loginUser = accountService.getLoginUser();
        List<Role> roles = loginUser.getRoles();
        List<Menu> menus = new ArrayList<>();
        for (Role role : roles) {
            menus.addAll(menuService.getMenuByRoleId(role.getId()));
        }
        return Result.success(TreeUtil.listToTree(menus));
    }
}
