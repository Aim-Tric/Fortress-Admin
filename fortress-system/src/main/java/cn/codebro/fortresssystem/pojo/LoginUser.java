package cn.codebro.fortresssystem.pojo;

import java.io.Serializable;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-23 09:32:32
 */
public class LoginUser implements Serializable {
    private String loginId;
    private String type;
    private String account;
    private String password;
    private String validateCode;

    public String getLoginId() {
        return loginId;
    }

    public LoginUser setLoginId(String loginId) {
        this.loginId = loginId;
        return this;
    }

    public String getType() {
        return type;
    }

    public LoginUser setType(String type) {
        this.type = type;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public LoginUser setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public LoginUser setValidateCode(String validateCode) {
        this.validateCode = validateCode;
        return this;
    }
}
