package cn.codebro.fortresssystem.pojo.document;

import cn.codebro.fortress.common.exception.ModelException;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-28 22:02:43
 */
public enum SaveType {
    LOCAL(0),
    OSS(1);
    private final Integer value;

    SaveType(Integer value) {
        this.value = value;
    }

    public static SaveType valueOf(Integer value) {
        if (value == 0) {
            return LOCAL;
        } else if (value == 1) {
            return OSS;
        }
        throw new ModelException();
    }

    public Integer getValue() {
        return this.value;
    }

}
