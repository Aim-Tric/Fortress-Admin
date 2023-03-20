package cn.codebro.fortress.common.exception;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-23 00:36:50
 */
public class UserExistException extends RuntimeException {
    private final String username;

    public UserExistException(String username) {
        super("用户名已被注册！");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
