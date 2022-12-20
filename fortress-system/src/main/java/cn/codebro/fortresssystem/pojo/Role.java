package cn.codebro.fortresssystem.pojo;

import cn.codebro.fortresscommon.Entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@TableName(value = "F_ROLE", excludeProperty = {"auths", "menus"})
public class Role extends Entity implements Serializable {
    @TableId
    private String id;
    private String name;
    private String identify;
    private List<Auth> auths;
    private List<Menu> menus;

    public String getId() {
        return id;
    }

    public Role setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentify() {
        return identify;
    }

    public Role setIdentify(String identify) {
        this.identify = identify;
        return this;
    }

    public List<Auth> getAuths() {
        return auths;
    }

    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public Role setMenus(List<Menu> menus) {
        this.menus = menus;
        return this;
    }
}
