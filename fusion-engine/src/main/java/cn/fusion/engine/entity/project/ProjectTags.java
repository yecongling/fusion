package cn.fusion.engine.entity.project;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName ProjectTags
 * @Description 项目标签关联表 一对多
 * @Author ycl
 * @Date 2025/5/8 16:31
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "t_engine_project_tags", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class ProjectTags extends BaseEntity implements Serializable {
    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 标签id
     */
    private Long tagId;

}
