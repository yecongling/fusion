package cn.net.fusion.engine.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName EndpointProperties
 * @Description 端点配置属性
 * @Author ycl
 * @Date 2024/11/28 11:17
 * @Version 1.0
 */
@Table("t_engine_endpoint_properties")
public class EndpointProperties implements Serializable {

    @Serial
    private static final long serialVersionUID = 241343699855628548L;

    // 属性id
    @Id
    private String id;

    // 配置名
    private String name;

    // 所属端点配置id
    private String configId;

    // 标题
    private String title;

    // 提示信息
    private String tips;

    // 类型
    private String type;

    // 是否必填
    private Boolean required;

    // 默认值
    private String defaultValue;

    // 支持值
    private String allowedValues;

    // 应用端
    private String appliesTo;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(String allowedValues) {
        this.allowedValues = allowedValues;
    }

    public String getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(String appliesTo) {
        this.appliesTo = appliesTo;
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
