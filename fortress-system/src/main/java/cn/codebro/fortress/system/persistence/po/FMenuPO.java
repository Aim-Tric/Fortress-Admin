package cn.codebro.fortress.system.persistence.po;

import cn.codebro.fortress.common.model.BaseEntity;
import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Id;

/**
 * @author Guo Wentao
 * @version 1.0.0
 * Table: f_menu
 * @project fortress-admin
 */
@org.sagacity.sqltoy.config.annotation.Entity(tableName = "f_menu", comment = "", pk_constraint = "PRIMARY")
public class FMenuPO extends BaseEntity {

    /**
     * 主键
     */
    @Id(strategy = "generator", generator = "org.sagacity.sqltoy.plugins.id.impl.NanoTimeIdGenerator")
    @Column(name = "id", comment = "主键", length = 32L, type = java.sql.Types.VARCHAR, nullable = false)
    protected String id;
    /**
     * 父级主键
     */
    @Column(name = "parent", comment = "父级主键", length = 32L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String parent;
    /**
     * 显示名称
     */
    @Column(name = "name", comment = "显示名称", length = 200L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String name;
    /**
     * 图标名称
     */
    @Column(name = "icon_name", comment = "图标名称", length = 200L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String iconName;
    /**
     * 路由名称
     */
    @Column(name = "route_name", comment = "路由名称", length = 200L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String routeName;
    /**
     * 页面标题
     */
    @Column(name = "page_title", comment = "页面标题", length = 200L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String pageTitle;
    /**
     * 页面路径
     */
    @Column(name = "page_path", comment = "页面路径", length = 200L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String pagePath;
    /**
     * 菜单类型
     */
    @Column(name = "type", comment = "菜单类型; 0 目录; 1 页面; 2 按钮; 3 链接; ", length = 10L, type = java.sql.Types.INTEGER, nullable = true)
    protected Integer type;
    /**
     * 页面类型
     */
    @Column(name = "page_type", comment = "页面类型; 0 模板; 1 自定义;", length = 10L, type = java.sql.Types.INTEGER, nullable = true)
    protected Integer pageType;
    /**
     * 页面组件路径
     */
    @Column(name = "component_path", comment = "页面组件路径", length = 300L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String componentPath;
    /**
     * 状态
     */
    @Column(name = "status", comment = "状态; 0 隐藏; 1 正常;", length = 10L, type = java.sql.Types.INTEGER, nullable = true)
    protected Integer status;
    /**
     * 描述
     */
    @Column(name = "description", comment = "描述", length = 500L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String description;
    /**
     * 排序
     */
    @Column(name = "order_num", comment = "排序", length = 10L, type = java.sql.Types.INTEGER, nullable = true)
    protected Integer orderNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public String getComponentPath() {
        return componentPath;
    }

    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getOrderNum() {
        return orderNum;
    }

    @Override
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
