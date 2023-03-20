package cn.codebro.fortress.common.exception;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-23 00:31:42
 */
public class IncorrectUsernameOrPasswordException extends RuntimeException {
    private final String username;

    public IncorrectUsernameOrPasswordException(String username) {
        super("用户名或密码不正确！");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
