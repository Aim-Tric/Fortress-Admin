package cn.codebro.fortresssystem.pojo;

import cn.codebro.fortresscore.model.Entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@TableName(value = "F_DEPT")
public class Dept extends Entity implements Serializable {
    @TableId
    private String id;
    private String name;
    private String parentId;
    private List<Post> posts;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
