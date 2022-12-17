package cn.codebro.fortresssystem.config.mybatis;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-20 11:14:58
 */
@Component
public class CommonFieldHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Date currentTime = new Date();
        Object currentUserId = StpUtil.getLoginId();
        this.strictInsertFill(metaObject, "createTime", Date.class, currentTime);
        this.strictInsertFill(metaObject, "createdBy", Object.class, currentUserId);
        this.strictUpdateFill(metaObject, "updateTime", Date.class, currentTime);
        this.strictInsertFill(metaObject, "updatedBy", Object.class, currentUserId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updatedBy", Object.class, StpUtil.getLoginId());
    }
}
