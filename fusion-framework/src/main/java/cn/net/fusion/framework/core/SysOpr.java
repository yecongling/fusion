package cn.net.fusion.framework.core;

/**
 * @ClassName SysOpr
 * @Description 系统操作员
 * @Author 叶丛林
 * @Date 2024/11/4 20:43
 * @Version 1.0
 **/
public class SysOpr extends BaseEntity{
    private String userId = "00001";
    private String userName = "admin";

    private String ipAddress = "";

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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
