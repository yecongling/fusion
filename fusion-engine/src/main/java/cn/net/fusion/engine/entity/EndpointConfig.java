package cn.net.fusion.engine.entity;

import cn.net.fusion.framework.config.EntityInsertListener;
import cn.net.fusion.framework.config.EntityUpdateListener;
import cn.net.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EndpointConfig
 * @Description 端点配置
 * @Author ycl
 * @Date 2024/11/26 09:19
 * @Version 1.0
 */
@Table(value = "t_engine_endpoint_config", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class EndpointConfig extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5039736494205830358L;

    // 配置 id
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String id;

    // 端点配置名称
    @NotBlank(message = "端点配置名称不能为空！")
    private String configName;

    // 图标
    private String icon;

    // 所属分类
    private String typeId;

    // 支持的模式
    @NotBlank(message = "端点支持模式不能为空！")
    private String supportedMode;

    // 描述
    private String description;

    @Column(ignore = true)
    private Boolean isConfig = Boolean.TRUE;

    // 使用flex对实体进行操作时，忽略该字段的操作，自己手动处理
    @Column(ignore = true)
    private List<EndpointConfigProperty> endpointProperties;

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

    public Boolean getConfig() {
        return isConfig;
    }

    public void setConfig(Boolean config) {
        isConfig = config;
    }

    public List<EndpointConfigProperty> getEndpointProperties() {
        if (endpointProperties == null) {
            endpointProperties = new ArrayList<>();
        }
        return endpointProperties;
    }

    public void setEndpointProperties(List<EndpointConfigProperty> endpointProperties) {
        this.endpointProperties = endpointProperties;
    }
}
