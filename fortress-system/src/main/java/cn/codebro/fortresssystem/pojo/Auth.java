package cn.codebro.fortresssystem.pojo;

import cn.codebro.fortresscommon.Entity;
import cn.codebro.fortresscommon.tree.Treetify;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@TableName(value = "F_AUTH", excludeProperty = {"children"})
public class Auth extends Entity implements Serializable, Treetify<String, Auth> {
    @TableId
    private String id;
    private String parent;
    private String name;
    private String identify;
    private List<Treetify<String, Auth>> children;

    @Override
    public String getId() {
        return id;
    }

    public Auth setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getParent() {
        return parent;
    }

    @Override
    public boolean hasChildren() {
        return children != null && children.size() > 0;
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

    @Override
    public List<Treetify<String, Auth>> getChildren() {
        return children;
    }

    @Override
    public void addChildren(Treetify<String, Auth> child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

}
