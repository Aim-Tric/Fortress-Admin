package cn.codebro.fortress.common.model;

/**
 * @author Guo wentao
 * @date 2022/10/10
 */
public class Result {
    private Integer code;
    private String message;
    private Object data;

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    private void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        return new Result(200, "执行成功");
    }

    public static Result success(Object data) {
        Result result = new Result(200, "执行成功");
        result.data = data;
        return result;
    }

    public static Result fail() {
        return new Result(400, "系统异常");
    }

    public static Result fail(String message) {
        return new Result(400, message);
    }

    public static Result fail(Integer code, String message) {
        return new Result(code, message);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
