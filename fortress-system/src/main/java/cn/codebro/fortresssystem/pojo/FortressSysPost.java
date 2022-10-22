package cn.codebro.fortresssystem.pojo;

import java.io.Serializable;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public class FortressSysPost implements Serializable {
    private String id;
    private String name;

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
}
