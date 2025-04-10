package cn.fusion.framework.core;

import cn.fusion.framework.enums.HttpCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Response
 * @Description 封装统一结果集
 * @Author ycl
 * @Date 2024/11/1 17:41
 * @Version 1.0
 */
@Setter
@Getter
public class Response<T> {

    /**
     * 状态码 和枚举中定义的基本对应
     */
    private Integer code;

    /**
     * 操作的状态 true 成功  false 失败
     */
    private Boolean success;

    /**
     * 返回的信息 包括成功信息 失败信息等
     */
    private String message;

    /**
     * 返回给前台的数据
     */
    private T data;

    /**
     * 无参构造函数
     */
    public Response() {
    }

    public Response(Integer code, Boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 响应成功信息
     *
     * @return 响应结果
     */
    public static <T> Response<T> success() {
        Response<T> response = new Response<T>();
        response.setSuccess(true);
        response.setCode(HttpCodeEnum.SUCCESS.getCode());
        response.setMessage(HttpCodeEnum.SUCCESS.getMessage());
        return response;
    }

    /**
     * 响应带数据的成功结果
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 结果
     */
    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<T>();
        response.setSuccess(true);
        response.setCode(HttpCodeEnum.SUCCESS.getCode());
        response.setMessage(HttpCodeEnum.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    /**
     * 返回成功
     *
     * @param msg  信息
     * @param data 数据
     * @param <T>  类型
     * @return 结果
     */
    public static <T> Response<T> success(String msg, T data) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setCode(HttpCodeEnum.SUCCESS.getCode());
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static <T> Response<T> fail() {
        Response<T> response = new Response<T>();
        response.setSuccess(false);
        response.setCode(HttpCodeEnum.ERROR.getCode());
        response.setMessage(HttpCodeEnum.ERROR.getMessage());
        return response;
    }

    public static <T> Response<T> fail(String msg, T data) {
        Response<T> r = new Response<T>();
        r.setCode(HttpCodeEnum.ERROR.getCode());
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static <T> Response<T> fail(String msg) {
        return fail(HttpCodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Response<T> fail(Integer code, String msg) {
        Response<T> r = new Response<T>();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    /**
     * 判断当前结果是否成功
     *
     * @return true 成功 false 失败
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.code != null && this.code == 200;
    }

}
