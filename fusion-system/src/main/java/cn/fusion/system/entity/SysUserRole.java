package cn.fusion.system.entity;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName SysUserRole
 * @Description 系统用户角色关联表
 * @Author ycl
 * @Date 2024/12/27 13:56
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "t_sys_user_role", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysUserRole extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1912915195858017472L;

    /**
     * id字段
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @Column(ignore = true)
    private String username;
    @Column(ignore = true)
    private String realName;
    @Column(ignore = true)
    private int sex;

    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /**
     * 操作IP
     */
    private String operateIp;

}
