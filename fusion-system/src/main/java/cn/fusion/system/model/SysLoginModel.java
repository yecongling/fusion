package cn.fusion.system.model;

/**
 * @ClassName SysLoginModel
 * @Description 系统登录模型
 * @Author ycl
 * @Date 2024/11/5 10:14
 * @Version 1.0
 */
public class SysLoginModel {
    // 用户名
    private String username;
    // 密码
    private String password;
    // 验证码
    private String captcha;
    // 验证码key
    private String checkKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }
}
