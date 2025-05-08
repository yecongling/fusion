package cn.fusion.engine.entity.project;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.*;
import com.mybatisflex.core.keygen.KeyGenerators;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName Project
 * @Description 项目实例
 * @Author ycl
 * @Date 2025/1/22 11:50
 * @Version 1.0
 */
@Setter
@Getter
@Table(value = "t_engine_project", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class Project extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6595370169713034744L;

    /**
     * 项目ID
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空")
    @Size(min = 1, max = 128)
    private String name;

    /**
     * 项目类型
     */
    @NotNull(message = "项目类型不能为空，type字段必传")
    private Integer type;

    /**
     * 项目状态（正常、异常、部分异常、未启动）
     */
    @Column(onInsertValue = "0")
    private Integer status;

    /**
     * 优先级(默认值为5)
     */
    @Column(onInsertValue = "5")
    private Integer priority;

    /**
     * 日志级别
     */
    @Column(onInsertValue = "1")
    private Integer logLevel;

    /**
     * 背景图
     */
    private String background;

    /**
     * 备注
     */
    private String remark;

    /**
     * 项目标签
     */
    @RelationOneToMany(targetField = "projectId", selfField = "id")
    private ProjectTags tags;

}
