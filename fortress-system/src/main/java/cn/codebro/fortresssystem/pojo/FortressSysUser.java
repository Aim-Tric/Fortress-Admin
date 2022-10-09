package cn.codebro.fortresssystem.pojo;

/**
 * @author Guo wentao
 * @date 2022/10/9
 */
public class FortressSysUser {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private Integer sex;
    private String phone;
    private String email;

    private FortressSysPost post;
    private FortressSysDept dept;
    private FortressSysDept role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FortressSysPost getPost() {
        return post;
    }

    public void setPost(FortressSysPost post) {
        this.post = post;
    }

    public FortressSysDept getDept() {
        return dept;
    }

    public void setDept(FortressSysDept dept) {
        this.dept = dept;
    }

    public FortressSysDept getRole() {
        return role;
    }

    public void setRole(FortressSysDept role) {
        this.role = role;
    }
}
