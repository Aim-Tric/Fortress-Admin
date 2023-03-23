package cn.codebro.fortress.system.persistence.po;

import cn.codebro.fortress.common.model.BaseEntity;
import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Entity;
import org.sagacity.sqltoy.config.annotation.Id;

/**
 * @author Guo Wentao
 * @version 1.0.0
 * Table: f_role_menu
 * @project fortress-admin
 */
@Entity(tableName = "f_role_menu", comment = "", pk_constraint = "PRIMARY")
public class FRoleMenuPO extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 5342798958615797664L;

    /**
     * 主键
     */
    @Id(strategy = "generator", generator = "org.sagacity.sqltoy.plugins.id.impl.NanoTimeIdGenerator")
    @Column(name = "id", comment = "主键", length = 32L, type = java.sql.Types.VARCHAR, nullable = false)
    protected String id;
    /**
     * 角色主键
     */
    @Column(name = "role_id", comment = "角色主键", length = 32L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String roleId;
    /**
     * 菜单主键
     */
    @Column(name = "menu_id", comment = "菜单主键", length = 32L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String menuId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
