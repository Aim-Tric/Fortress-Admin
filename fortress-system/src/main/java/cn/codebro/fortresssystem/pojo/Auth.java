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
    private String name;
    private String identity;

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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

}
