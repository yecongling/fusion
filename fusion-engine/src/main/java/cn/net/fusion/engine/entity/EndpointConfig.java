package cn.net.fusion.engine.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName EndpointConfig
 * @Description 端点配置
 * @Author ycl
 * @Date 2024/11/26 09:19
 * @Version 1.0
 */
@Table("t_engine_endpoint_config")
public class EndpointConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = -5039736494205830358L;

    // 配置 id
    @Id
    private String id;

    // 端点配置名称
    private String configName;

    // 图标
    private String icon;

    // 所属分类
    private String typeId;

    // 支持的模式
    private String supportedMode;

    // 描述
    private String description;

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

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSupportedMode() {
        return supportedMode;
    }

    public void setSupportedMode(String supportedMode) {
        this.supportedMode = supportedMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
