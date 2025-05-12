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
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysMenu
 * @Description 系统菜单实体类
 * @Author ycl
 * @Date 2024/11/8 11:58
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "t_sys_menu", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysMenu extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6376947729586532271L;
    /**
     * id
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 父id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限编码，例如：“sys:schedule:list,sys:schedule:info”,多个逗号隔开
     */
    private String perms;
    /**
     * 权限策略1显示2禁用
     */
    private String permsType;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件
     */
    private String component;

    /**
     * 组件名字
     */
    private String componentName;

    /**
     * 路径
     */
    private String url;
    /**
     * 一级菜单跳转地址
     */
    private String redirect;

    /**
     * 菜单排序
     */
    private Double sortNo;

    /**
     * 类型（0：一级菜单；1：子菜单 ；2：按钮权限）
     */
    private Integer menuType;

    /**
     * 是否叶子节点: 1:是  0:不是
     */
    @Column("is_leaf")
    private boolean leaf;

    /**
     * 是否路由菜单: 0:不是  1:是（默认值1）
     */
    @Column("is_route")
    private boolean route = false;

    /**
     * 是否缓存页面: 0:不是  1:是（默认值1）
     */
    private boolean keepAlive;

    /**
     * 描述
     */
    private String description;

    /**
     * 删除状态 0正常 1已删除
     */
    @Column(isLogicDelete = true)
    private Boolean delFlag = false;

    /**
     * 是否配置菜单的数据权限 1是0否 默认0
     */
    private Boolean ruleFlag = Boolean.FALSE;

    /**
     * 是否隐藏路由菜单: 0否,1是（默认值0）
     */
    private boolean hidden = false;

    /**
     * 是否隐藏Tab: 0否,1是（默认值0）
     */
    private boolean hideTab = false;

    /**
     * 按钮权限状态(0无效1有效)
     */
    private Boolean status;

    /**
     * alwaysShow
     */
    private boolean alwaysShow;

    /**
     * 外链菜单打开方式 0/内部打开 1/外部打开
     */
    private boolean internalOrExternal;

    /**
     * 下级子菜单（功能按钮）
     */
    @Column(ignore = true)
    private List<SysMenu> children;

    public List<SysMenu> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

}
