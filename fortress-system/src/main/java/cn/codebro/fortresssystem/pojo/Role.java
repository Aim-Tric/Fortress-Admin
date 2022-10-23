package cn.codebro.fortresssystem.pojo;

import cn.codebro.fortresscommon.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@TableName(value = "f_role", excludeProperty = {"auths"})
public class Role extends Model implements Serializable {
    @TableId
    private String id;
    private String name;
    private List<Auth> auths;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Auth> getAuths() {
        return auths;
    }

    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }
}
