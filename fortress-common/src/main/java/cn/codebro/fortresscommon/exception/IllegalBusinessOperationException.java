package cn.codebro.fortresscommon.exception;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-01 10:02:12
 */
public class IllegalBusinessOperationException extends RuntimeException {

    private static final Integer DEFAULT_ERROR_CODE = 500;
    private Integer code;

    private IllegalBusinessOperationException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public IllegalBusinessOperationException setCode(Integer code) {
        this.code = code;
        return this;
    }

    public static IllegalBusinessOperationException dump(String message) {
        return new IllegalBusinessOperationException(DEFAULT_ERROR_CODE, message);
    }
}
