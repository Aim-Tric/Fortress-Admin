package cn.codebro.fortresssystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-24 22:19:20
 */
@TableName(value = "f_user", excludeProperty = {"roles"})
public class UserDTO implements Serializable {
    @TableId
    private String id;
    private String username;
    private String password;
    private String nickname;
    private Integer sex;
    private String phone;
    private String email;
    private Integer status;
    private String post;
    private String dept;
    private List<String> roles;

    public String getId() {
        return id;
    }

    public UserDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public UserDTO setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getPost() {
        return post;
    }

    public UserDTO setPost(String post) {
        this.post = post;
        return this;
    }

    public String getDept() {
        return dept;
    }

    public UserDTO setDept(String dept) {
        this.dept = dept;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
