package cn.codebro.fortresssystem.pojo;

import cn.codebro.fortresscommon.Model;
import cn.codebro.fortresscommon.tree.Treetify;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-01 21:39:04
 */
@TableName(value = "f_menu", excludeProperty = {"children"})
public class Menu extends Model implements Serializable, Treetify<String, Menu> {
    @TableId
    private String id;
    private String parent;
    private String name;
    private String iconName;
    private String routeName;
    private String pageTitle;
    private String pagePath;
    private Integer type;
    private String componentPath;
    private Integer status;
    private String description;

    private List<Treetify<String, Menu>> children;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getParent() {
        return parent;
    }

    @Override
    public boolean hasChildren() {
        return children != null && children.size() > 0;
    }

    @Override
    public Collection<Treetify<String, Menu>> getChildren() {
        return children;
    }

    @Override
    public void addChildren(Treetify<String, Menu> obj) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(obj);
    }

    public Menu setId(String id) {
        this.id = id;
        return this;
    }

    public Menu setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public String getName() {
        return name;
    }

    public Menu setName(String name) {
        this.name = name;
        return this;
    }

    public String getIconName() {
        return iconName;
    }

    public Menu setIconName(String iconName) {
        this.iconName = iconName;
        return this;
    }

    public String getRouteName() {
        return routeName;
    }

    public Menu setRouteName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public Menu setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
        return this;
    }

    public String getPagePath() {
        return pagePath;
    }

    public Menu setPagePath(String pagePath) {
        this.pagePath = pagePath;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Menu setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getComponentPath() {
        return componentPath;
    }

    public Menu setComponentPath(String componentPath) {
        this.componentPath = componentPath;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Menu setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Menu setChildren(List<Treetify<String, Menu>> children) {
        this.children = children;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Menu setDescription(String description) {
        this.description = description;
        return this;
    }
}
