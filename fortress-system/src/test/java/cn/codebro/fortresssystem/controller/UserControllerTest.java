package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.mapper.FortressUserMapper;
import cn.codebro.fortresssystem.pojo.FortressSysUser;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-20 08:39:26
 */
@SpringBootTest
public class UserControllerTest {

    @Resource
    UserController controller;

    @Test
    public void page() {
        int page = 1, pageSize = 10;
        Result result = controller.page(page, pageSize);
        System.out.println(((Page<FortressSysUser>) result.getData()).getRecords());
    }

    @Test
    public void add() {
        FortressSysUser user = new FortressSysUser();
        user.setUsername("Tric2");
        user.setNickname("Tric2");
        user.setPassword("123456");
        user.setStatus(1);
        user.setEmail("473074764@qq.com");
        user.setPhone("138****2212");
        user.setSex(2);
        controller.add(user);
    }

    @Test
    public void delete() {
        controller.delete("1582963012855709698");
    }

    @Test
    public void update() {
        FortressSysUser user = new FortressSysUser();
        user.setId("1582963233648062465");
        user.setUsername("Tric");
        user.setNickname("Tric");
        controller.update(user);
    }
}
