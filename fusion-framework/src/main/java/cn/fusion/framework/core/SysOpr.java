package cn.fusion.framework.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.Column;

import java.util.List;

/**
 * @ClassName SysOpr
 * @Description 系统操作员
 * @Author 叶丛林
 * @Date 2024/11/4 20:43
 * @Version 1.0
 **/
public class SysOpr extends BaseEntity{

    /**
     * 当前登录的用户ID
     */
    @Column(ignore = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 当前登录的用户名
     */
    @Column(ignore = true)
    private String userName;

    /**
     * 登录首页地址
     */
    @Column(ignore = true)
    private String homePath;

    /**
     * 用户当前登录的ip
     */
    @Column(ignore = true)
    private String loginIp;

    /**
     * 用户当前登录的角色（有可能很多个角色，默认选中第一个）
     */
    @Column(ignore = true)
    private String currentRoleId;

    /**
     * 用户的角色列表
     */
    @Column(ignore = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private List<Long> roleIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getCurrentRoleId() {
        return currentRoleId;
    }

    public void setCurrentRoleId(String currentRoleId) {
        this.currentRoleId = currentRoleId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
