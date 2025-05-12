package cn.fusion.system.entity;

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

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName SysRoleMenu
 * @Description 系统角色菜单关联表
 * @Author ycl
 * @Date 2024/12/27 13:56
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "t_sys_role_menu", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysRoleMenu extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4998180998954311669L;

    // 配置id
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    // 角色id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    // 菜单id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;

    // 操作ip
    private String operateIp;

}
