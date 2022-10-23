package cn.codebro.fortresscommon.exception;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-23 00:29:01
 */
public class UnknownUserException extends RuntimeException {
    private final String username;

    public UnknownUserException(String username) {
        super("用户不存在");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
