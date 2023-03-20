package cn.codebro.fortress.system.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-21 12:51:10
 */
@TableName("f_system")
public class SystemInfo {
    @TableId
    private String id;
    private String systemName;
    private Boolean initialized;
    private Date initializeTime;
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Boolean getInitialized() {
        return initialized;
    }

    public void setInitialized(Boolean initialized) {
        this.initialized = initialized;
    }

    public Date getInitializeTime() {
        return initializeTime;
    }

    public void setInitializeTime(Date initializeTime) {
        this.initializeTime = initializeTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
