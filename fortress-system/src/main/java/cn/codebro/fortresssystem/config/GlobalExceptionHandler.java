package cn.codebro.fortresssystem.config;

import cn.codebro.fortresscommon.Result;
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
        System.out.println("通用异常捕获");
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(SaTokenException.class)
    public Result saTokenHandler(SaTokenException e) {
        System.out.println("SaToken异常捕获");
        e.printStackTrace();
        return Result.fail();
    }
}
