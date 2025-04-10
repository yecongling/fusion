package cn.fusion.system.entity;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.SysOpr;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.*;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.mybatisflex.core.mask.Masks;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysUser
 * @Description 系统用户模块
 * @Author 叶丛林
 * @Date 2024/11/4 21:00
 * @Version 1.0
 **/
@Getter
@Setter
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
@Table(value = "t_sys_user", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysUser extends SysOpr implements Serializable {
    @Serial
    private static final long serialVersionUID = -1283938764867538048L;

    /**
     * 数据库主键，唯一ID
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /* 用户登录账号 */
    @NotEmpty(message = "用户名不能为空！")
    private String username;

    /* 用户登录密码 */
    @NotEmpty(message = "密码不能为空！")
    @Size(min = 8, message = "密码长度不能小于8！")
    private String password;

    /**
     * md5密码盐
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String salt;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 生日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;

    /**
     * 电子邮件
     */
    @ColumnMask(Masks.EMAIL)
    private String email;

    /**
     * 电话
     */
    @ColumnMask(Masks.MOBILE)
    private String phone;

    /**
     * 部门code(当前选择登录部门)
     */
    private String orgCode;

    /**
     * 部门名称
     */
    private transient String orgCodeTxt;

    /**
     * 状态(1：正常  2：冻结 ）
     */
    private Integer status;

    /**
     * 删除状态（0，正常，1已删除）
     */
    @Column(isLogicDelete = true)
    private Boolean delFlag;

    /**
     * 工号，唯一键
     */
    private String workNo;

    /**
     * 职务，关联职务表
     */
    private String post;

    /**
     * 座机号
     */
    private String telephone;
    /**
     * 同步工作流引擎1同步0不同步
     */
    private Boolean activitySync;

    /**
     * 身份（0 普通成员 1 上级）
     */
    private Integer userIdentity;

    /**
     * 负责部门
     */
    private String departIds;

    /**
     * 多租户id配置，编辑用户的时候设置
     */
    private String relTenantIds;

    /**
     * 设备id taro（React开发）推送用
     */
    private String clientId;

    public void setId(Long id) {
        this.id = id;
        // 设置父级属性
        this.setUserId(id);
    }

    public void setUsername(String username) {
        this.username = username;
        this.setUserName(username);
    }
}
