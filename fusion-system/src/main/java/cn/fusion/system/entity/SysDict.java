package cn.fusion.system.entity;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName SysDict
 * @Description 数据字典实体类
 * @Author ycl
 * @Date 2025/3/27 13:37
 * @Version 1.0
 **/
@Table(value = "t_sys_dict", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysDict extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @Id
    private String dictCode;

}
