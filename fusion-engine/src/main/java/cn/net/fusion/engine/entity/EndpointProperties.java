package cn.net.fusion.engine.entity;

import cn.net.fusion.framework.config.EntityInsertListener;
import cn.net.fusion.framework.config.EntityUpdateListener;
import cn.net.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName EndpointProperties
 * @Description 端点配置属性
 * @Author ycl
 * @Date 2024/11/28 11:17
 * @Version 1.0
 */
@Table(value = "t_engine_endpoint_properties", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class EndpointProperties extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 241343699855628548L;

    // 属性id
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String id;

    // 配置名
    @NotBlank(message = "属性配置名不能为空！")
    private String name;

    // 所属端点配置id
    private String configId;

    // 标题
    private String title;

    // 提示信息
    private String tips;

    // 类型（针对前端控件的类型，如input、select、checkbox等）
    private String type;

    // 是否必填
    private Boolean required;

    // 默认值
    private String defaultValue;

    // 支持值
    private String allowedValues;

    // 应用端
    private String appliesTo;

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
}
