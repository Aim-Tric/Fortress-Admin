package cn.codebro.fortress.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-19 23:51:58
 */
public abstract class BaseEntity implements Serializable {
    private Boolean deleted;
    private Integer orderNum;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
