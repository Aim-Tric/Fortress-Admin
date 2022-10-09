package cn.codebro.fortresssystem.pojo;

import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public class FortressSysDept {
    private String id;
    private String name;
    private String parentId;
    private List<FortressSysPost> posts;

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

    public List<FortressSysPost> getPosts() {
        return posts;
    }

    public void setPosts(List<FortressSysPost> posts) {
        this.posts = posts;
    }
}
