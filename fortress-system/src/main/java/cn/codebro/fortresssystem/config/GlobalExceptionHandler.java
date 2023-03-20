package cn.codebro.fortresssystem.config;

import cn.codebro.fortress.common.model.Result;
import cn.dev33.satoken.exception.SaTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-23 20:00:36
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result commonHandler(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler({SaTokenException.class})
    public Result saTokenHandler(SaTokenException e) {
        e.printStackTrace();
        return Result.fail(501, "用户未登录");
    }
}
