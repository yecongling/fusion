package cn.fusion.engine.entity.project;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName EndpointConfigProperty
 * @Description 端点配置属性
 * @Author ycl
 * @Date 2024/11/28 11:17
 * @Version 1.0
 */
@Setter
@Getter
@Table(value = "t_engine_endpoint_config_property", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class EndpointConfigProperty extends BaseEntity implements Serializable {

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

}
