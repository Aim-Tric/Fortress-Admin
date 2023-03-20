package cn.codebro.fortress.common.exception;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-23 22:49:20
 */
public class ModelException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(ModelException.class);

    public ModelException() {
        logger.error("异常信息: 无");
    }

    public ModelException(String message) {
        super(message);
        logger.error(message);
    }

    public ModelException(String message, Throwable cause) {
        super(message, cause);
        logger.error(StrUtil.format("异常信息: {}", message));
    }

    public ModelException(Throwable cause) {
        super(cause);
        logger.error("异常信息", cause);
    }

    public ModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        logger.error(StrUtil.format(
                "异常信息: {}, enableSuppression: {}, writableStackTrace: {}",
                message, enableSuppression, writableStackTrace
        ), cause);
    }
}
