package cn.codebro.fortress.system.persistence.po;

import cn.codebro.fortress.common.model.BaseEntity;
import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Entity;
import org.sagacity.sqltoy.config.annotation.Id;

/**
 * @author Guo Wentao
 * @version 1.0.0
 * Table: f_user_role
 * @project fortress-admin
 */
@Entity(tableName = "f_user_role", comment = "", pk_constraint = "PRIMARY")
public class FUserRolePO extends BaseEntity {

    private static final long serialVersionUID = 5023217841209774514L;

    /**
     * 主键
     */
    @Id(strategy = "generator", generator = "org.sagacity.sqltoy.plugins.id.impl.NanoTimeIdGenerator")
    @Column(name = "id", comment = "主键", length = 32L, type = java.sql.Types.VARCHAR, nullable = false)
    protected String id;
    /**
     * 角色id
     */
    @Column(name = "role_id", comment = "角色id", length = 32L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String roleId;
    /**
     * 用户id
     */
    @Column(name = "user_id", comment = "用户id", length = 32L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
