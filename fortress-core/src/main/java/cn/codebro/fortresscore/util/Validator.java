package cn.codebro.fortresscore.util;

import java.util.Objects;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-11-15 10:25:05
 */
public class Validator {

    public static void notNull(Object param) {
        if (Objects.isNull(param)) {
            throw new RuntimeException("参数不能为空");
        }
    }

    public static void notNull(Object param, String message) {
        if (Objects.isNull(param)) {
            throw new RuntimeException(message);
        }
    }

}
