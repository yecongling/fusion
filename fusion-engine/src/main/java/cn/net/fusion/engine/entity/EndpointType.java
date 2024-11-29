package cn.net.fusion.engine.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName EndpointType
 * @Description 端点类型
 * @Author ycl
 * @Date 2024/11/23 17:46
 * @Version 1.0
 */
@TableName("t_engine_endpoint_type")
public class EndpointType implements Serializable {

    @Serial
    private static final long serialVersionUID = 5893206708013699530L;

    // 类型id
    @TableId
    private String id;

    // 类型名
    private String typeName;

    // 上级id
    private String parentId;
    // 创建人
    private String createBy;
    // 创建时间
    private Date createTime;
    // 更新人
    private String updateBy;
    // 更新时间
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
