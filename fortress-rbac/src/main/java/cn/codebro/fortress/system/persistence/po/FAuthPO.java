/**
 * @Generated by sagacity-quickvo 5.0
 */
package cn.codebro.fortress.system.persistence.po;

import cn.codebro.fortress.common.model.BaseEntity;
import cn.codebro.fortress.common.model.TreeableEntity;
import cn.codebro.fortress.common.util.tree.Treeable;
import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Entity;
import org.sagacity.sqltoy.config.annotation.Id;

import java.util.Collection;

/**
 * @author Guo Wentao
 * @version 1.0.0
 * Table: f_auth
 * @project fortress-admin
 */
@Entity(tableName = "f_auth", comment = "", pk_constraint = "PRIMARY")
public class FAuthPO extends TreeableEntity<String, FAuthPO> {

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
     * 权限名称
     */
    @Column(name = "name", comment = "权限名称", length = 200L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String name;
    /**
     * 权限标识
     */
    @Column(name = "identify", comment = "权限标识", length = 200L, type = java.sql.Types.VARCHAR, nullable = true)
    protected String identify;

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

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

}
