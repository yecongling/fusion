package cn.net.fusion.framework.core;

import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description 所有bean的基类，存储创建人、创建时间、更新人、更新时间
 * @Author ycl
 * @Date 2024/12/14 10:49
 * @Version 1.0
 */
public class BaseEntity {
    // 创建人
    private String createBy;

    // 创建时间
    private Date createTime;

    // 更新人
    private String updateBy;

    // 更新时间
    private Date updateTime;

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
