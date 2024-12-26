package cn.net.fusion.system.entity;

import cn.net.fusion.framework.config.EntityInsertListener;
import cn.net.fusion.framework.config.EntityUpdateListener;
import cn.net.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName SysRole
 * @Description 系统用户
 * @Author ycl
 * @Date 2024/12/26 13:35
 * @Version 1.0
 */
@Table(value = "t_sys_role", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysRole extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5972546709725498012L;

    /**
     * id
     */
    @Id
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 角色状态
     */
    private Boolean status;

    /**
     * 角色描述
     */
    private String description;


}
