package cn.net.fusion.system.entity;

import cn.net.fusion.framework.config.EntityInsertListener;
import cn.net.fusion.framework.config.EntityUpdateListener;
import cn.net.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;

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
@Table(value = "t_sys_menu", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysMenu extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6376947729586532271L;
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 父id
     */
    private String parentId;

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
    private boolean isLeaf;

    /**
     * 是否路由菜单: 0:不是  1:是（默认值1）
     */
    private boolean isRoute = true;

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
    private Integer delFlag = 0;

    /**
     * 是否配置菜单的数据权限 1是0否 默认0
     */
    private Integer ruleFlag = 0;

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
    private Integer status;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getPermsType() {
        return permsType;
    }

    public void setPermsType(String permsType) {
        this.permsType = permsType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Double getSortNo() {
        return sortNo;
    }

    public void setSortNo(Double sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isRoute() {
        return isRoute;
    }

    public void setRoute(boolean route) {
        isRoute = route;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getRuleFlag() {
        return ruleFlag;
    }

    public void setRuleFlag(Integer ruleFlag) {
        this.ruleFlag = ruleFlag;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHideTab() {
        return hideTab;
    }

    public void setHideTab(boolean hideTab) {
        this.hideTab = hideTab;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public boolean isInternalOrExternal() {
        return internalOrExternal;
    }

    public void setInternalOrExternal(boolean internalOrExternal) {
        this.internalOrExternal = internalOrExternal;
    }

    public List<SysMenu> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }
}
