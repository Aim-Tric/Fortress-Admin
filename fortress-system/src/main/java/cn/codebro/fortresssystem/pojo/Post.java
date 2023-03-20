package cn.codebro.fortresssystem.pojo;

import cn.codebro.fortress.common.model.Entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
@TableName(value = "F_POST")
public class Post extends Entity implements Serializable {
    @TableId
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
