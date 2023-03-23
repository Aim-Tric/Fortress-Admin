package cn.codebro.fortress.system.controller.param;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-23 09:32:32
 */
public class LoginParam implements Serializable {
    private String loginId;
    private String type;
    @NotBlank(message = "登录用户名不能为空")
    private String account;
    @NotBlank(message = "登录密码不能为空")
    private String password;
    private String validateCode;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
