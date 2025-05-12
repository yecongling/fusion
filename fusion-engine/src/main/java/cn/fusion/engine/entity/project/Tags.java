package cn.fusion.engine.entity.project;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * @ClassName Tags
 * @Description 项目标签
 * @Author ycl
 * @Date 2025/5/8 16:08
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "t_tags", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class Tags extends BaseEntity implements Serializable {

    /**
     * 标签id
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空")
    private String name;

    /**
     * 标签类型
     */
    private String type;

}
