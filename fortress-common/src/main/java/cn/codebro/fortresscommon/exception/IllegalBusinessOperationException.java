package cn.codebro.fortresscommon.exception;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-01 10:02:12
 */
public class IllegalBusinessOperationException extends RuntimeException {

    private static final Integer DEFAULT_ERROR_CODE = 500;
    private Integer code;

    private String debugMessage;

    public IllegalBusinessOperationException(Integer code, String message, String debugMessage) {
        super(message);
        this.code = code;
        this.debugMessage = debugMessage;
    }

    public Integer getCode() {
        return code;
    }

    public IllegalBusinessOperationException setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
