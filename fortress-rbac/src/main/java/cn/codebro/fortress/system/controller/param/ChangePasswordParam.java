package cn.codebro.fortress.system.controller.param;

import java.io.Serializable;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-19 14:36:58
 */
public class ChangePasswordParam implements Serializable {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
