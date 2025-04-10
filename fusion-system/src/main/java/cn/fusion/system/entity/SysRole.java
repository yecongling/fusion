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
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName SysRole
 * @Description 系统用户
 * @Author ycl
 * @Date 2024/12/26 13:35
 * @Version 1.0
 */
@Setter
@Getter
@Table(value = "t_sys_role", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysRole extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5972546709725498012L;

    /**
     * id 后端生成
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色编码 用户填写
     */
    @NotBlank(message = "角色编码不能为空")
    private String roleCode;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色类型
     */
    private Integer roleType;

    /**
     * 角色状态
     */
    private Boolean status;

    /**
     * 角色备注
     */
    private String remark;

}
