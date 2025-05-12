package cn.fusion.framework.enums;

/**
 * @ClassName SysOperation
 * @Description 枚举系统操作类型
 * @Author ycl
 * @Date 2024/11/5 11:38
 * @Version 1.0
 */
public enum SysOperation {

    /**
     * 登录操作
     */
    LOGIN(100, "登录操作"),

    /**
     * 退出登录
     */
    LOGOUT(101, "退出登录");

    // 操作码
    private final Integer code;

    // 操作信息
    private final String message;

    SysOperation(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
