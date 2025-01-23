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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName Project
 * @Description 项目实例
 * @Author ycl
 * @Date 2025/1/22 11:50
 * @Version 1.0
 */
@Table(value = "t_engine_project", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class Project extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 6595370169713034744L;

    /**
     * 项目ID
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String id;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
