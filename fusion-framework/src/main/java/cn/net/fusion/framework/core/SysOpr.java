package cn.net.fusion.framework.core;

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
    private String userId;

    /**
     * 当前登录的用户名
     */
    private String userName;

    /**
     * 登录首页地址
     */
    private String homePath;

    /**
     * 用户当前登录的ip
     */
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
    private List<String> roleIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
