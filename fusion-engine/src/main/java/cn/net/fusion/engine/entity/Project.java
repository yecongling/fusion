package cn.net.fusion.engine.entity;

import cn.net.fusion.framework.config.EntityInsertListener;
import cn.net.fusion.framework.config.EntityUpdateListener;
import cn.net.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

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
    private long id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目类型
     */
    private int type;

    /**
     * 项目状态（正常、异常、部分异常、未启动）
     */
    private int status;

    /**
     * 优先级
     */
    private int priority;

    /**
     * 日志级别
     */
    private int logLevel;

    /**
     * 背景图
     */
    private String background;

    /**
     * 备注
     */
    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
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
