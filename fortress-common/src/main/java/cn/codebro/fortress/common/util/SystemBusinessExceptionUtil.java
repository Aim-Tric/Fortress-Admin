package cn.codebro.fortress.common.util;

import cn.codebro.fortress.common.exception.IllegalBusinessOperationException;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-18 19:18:41
 */
public class SystemBusinessExceptionUtil {
    private static final Integer DEFAULT_SYSTEM_EXCEPTION_CODE = 500;

    public static IllegalBusinessOperationException dump(String message, String debugMessage) {
        return new IllegalBusinessOperationException(DEFAULT_SYSTEM_EXCEPTION_CODE, message, debugMessage);
    }

    public static IllegalBusinessOperationException dump(String message) {
        return dump(message, "No debug message left. Please check the log file.");
    }

}
