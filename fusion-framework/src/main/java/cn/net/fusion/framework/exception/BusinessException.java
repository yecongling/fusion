package cn.net.fusion.framework.exception;

import cn.net.fusion.framework.enums.HttpCodeEnum;

/**
 * @ClassName BusinessException
 * @Description 通用业务异常处理类
 * @Author 叶丛林
 * @Date 2024/11/1 21:33
 * @Version 1.0
 **/
public class BusinessException extends RuntimeException {

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * 全参构造函数
     *
     * @param code    状态码
     * @param message 报错信息
     */
    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 只有错误消息的构造函数
     *
     * @param message 错误消息
     */
    public BusinessException(String message) {
        this(HttpCodeEnum.ERROR.getCode(), message);
    }

    /**
     * 构造函数
     *
     * @param code 错误枚举信息
     */
    public BusinessException(HttpCodeEnum code) {
        this(code.getCode(), code.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
