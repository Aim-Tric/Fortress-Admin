package cn.codebro.fortresscore.model;

import cn.codebro.fortresscore.exception.ModelException;
import cn.hutool.core.util.IdUtil;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-23 22:34:05
 */
public class StringIdentify implements Identify {
    private final String value;

    private StringIdentify(String value) {
        if (value == null || "".equals(value.trim())) {
            throw new ModelException("标识符不能为空");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StringIdentify valueOf(String id) {
        return new StringIdentify(id);
    }

    public static StringIdentify create() {
        return new StringIdentify(IdUtil.fastSimpleUUID());
    }

}
