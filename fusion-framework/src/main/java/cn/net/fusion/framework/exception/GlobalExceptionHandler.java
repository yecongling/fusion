package cn.net.fusion.framework.exception;


import cn.dev33.satoken.exception.NotLoginException;
import cn.net.fusion.framework.core.Response;
import cn.net.fusion.framework.enums.HttpCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @ClassName GlobalException
 * @Description 全局异常处理器
 * @Author 叶丛林
 * @Date 2024/11/1 21:37
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 日志记录工具
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务异常
     *
     * @param businessException 业务异常
     * @return 结果
     */
    @ExceptionHandler(BusinessException.class)
    public Response<Object> businessException(BusinessException businessException) {
        logger.error("业务异常 => code: {},原因是: {}", businessException.getCode(), businessException.getMessage());
        return Response.fail(businessException.getCode(), businessException.getMessage());
    }

    /**
     * 处理请求资源不存在的情况（404）
     *
     * @param e 异常信息
     * @return 错误信息
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<Object> noHandlerFoundException(HttpServletRequest e) {
        logger.error("请求地址错误（404）异常 => 请求方式： {}, 请求地址: {}", e.getMethod(), e.getServletPath());
        return Response.fail(HttpCodeEnum.RC404.getCode(), HttpCodeEnum.RC404.getMessage());
    }

    /**
     * 处理请求方式错误的异常（405）
     *
     * @param e 异常信息
     * @return 错误信息
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<Object> httpRequestMethodNotSupportedException(HttpServletRequest e) {
        logger.error("请求方式错误（405）异常 => 请求方式: {}, 请求地址：: {}", e.getMethod(), e.getServletPath());
        return Response.fail(HttpCodeEnum.RC405.getCode(), HttpCodeEnum.RC405.getMessage());
    }

    /**
     * 处理全局未知（600）异常 这里后续需要修改，记录完整的调用栈，不然不好排查错误
     *
     * @param e Exception
     * @return response
     */
    @ExceptionHandler(Exception.class)
    public Response<Object> globalException(Exception e) {
        logger.error("未知（600）异常 => 错误调用栈：{}", ExceptionUtils.getStackTrace(e));
        // 这里向前台返回主要的调用栈信息
        return Response.fail(HttpCodeEnum.ERROR.getCode(), ExceptionUtils.getRootCauseMessage(e));
    }

    /**
     * 处理未登录异常
     *
     * @param e 未登录异常
     * @return response
     */
    @ExceptionHandler(NotLoginException.class)
    public Response<Object> handleNotLoginException(NotLoginException e) {
        return Response.fail(HttpCodeEnum.RC101.getCode(), ExceptionUtils.getRootCauseMessage(e));
    }
}
