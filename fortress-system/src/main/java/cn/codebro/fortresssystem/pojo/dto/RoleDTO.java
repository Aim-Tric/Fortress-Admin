package cn.codebro.fortresssystem.pojo.dto;

import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-18 11:22:10
 */
public class RoleDTO {
    private String id;
    private String name;
    private String identify;
    private List<String> menus;
    private List<String> auths;

    public String getId() {
        return id;
    }

    public RoleDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentify() {
        return identify;
    }

    public RoleDTO setIdentify(String identify) {
        this.identify = identify;
        return this;
    }

    public List<String> getMenus() {
        return menus;
    }

    public RoleDTO setMenus(List<String> menus) {
        this.menus = menus;
        return this;
    }

    public List<String> getAuths() {
        return auths;
    }

    public RoleDTO setAuths(List<String> auths) {
        this.auths = auths;
        return this;
    }
}
