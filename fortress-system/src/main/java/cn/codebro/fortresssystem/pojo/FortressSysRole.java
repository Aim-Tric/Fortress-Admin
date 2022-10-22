package cn.codebro.fortresssystem.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public class FortressSysRole implements Serializable {

    private String id;
    private String name;
    private List<FortressSysAuth> auths;

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

    public List<FortressSysAuth> getAuths() {
        return auths;
    }

    public void setAuths(List<FortressSysAuth> auths) {
        this.auths = auths;
    }
}
