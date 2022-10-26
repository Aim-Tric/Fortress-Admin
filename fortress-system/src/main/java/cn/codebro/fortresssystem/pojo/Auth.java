package cn.codebro.fortresssystem.pojo;

import cn.codebro.fortresscommon.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@TableName(value = "f_auth")
public class Auth extends Model implements Serializable {
    @TableId
    private String id;
    private String parent;
    private String name;
    private String identify;

    public String getId() {
        return id;
    }

    public Auth setId(String id) {
        this.id = id;
        return this;
    }

    public String getParent() {
        return parent;
    }

    public Auth setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public String getName() {
        return name;
    }

    public Auth setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdentify() {
        return identify;
    }

    public Auth setIdentify(String identify) {
        this.identify = identify;
        return this;
    }
}
