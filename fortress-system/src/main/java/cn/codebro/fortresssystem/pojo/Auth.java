package cn.codebro.fortresssystem.pojo;

import cn.codebro.fortresscommon.Model;
import cn.codebro.fortresscommon.Treetify;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@TableName(value = "f_auth")
public class Auth extends Model implements Serializable, Treetify<String> {
    @TableId
    private String id;
    private String parent;
    private String name;
    private String identify;
    private List<Auth> children;

    @Override
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

    public List<Auth> getChildren() {
        return children;
    }

    public Auth setChildren(List<Auth> children) {
        this.children = children;
        return this;
    }

    @Override
    public String getParentId() {
        return parent;
    }
}
