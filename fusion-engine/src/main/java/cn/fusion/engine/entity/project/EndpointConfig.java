package cn.fusion.engine.entity.project;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
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

    // 支持重试（0、不支持 1、支持）
    private boolean supportRetry = false;

    // 支持调用处理策略（0、不支持 1、支持）
    private boolean strategy = false;

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

    public List<EndpointConfigProperty> getEndpointProperties() {
        if (endpointProperties == null) {
            endpointProperties = new ArrayList<>();
        }
        return endpointProperties;
    }
}