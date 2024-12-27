package cn.net.fusion.system.entity;

import cn.net.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName SysRoleMenu
 * @Description 系统角色菜单关联表
 * @Author ycl
 * @Date 2024/12/27 13:56
 * @Version 1.0
 */
@Table(value = "t_sys_role_menu")
public class SysRoleMenu extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4998180998954311669L;

    // 配置id
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String id;

    // 角色id
    private String roleId;

    // 菜单id
    private String menuId;

    // 操作ip
    private String operateIp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}
