package cn.net.fusion.framework.exception;


import cn.net.fusion.framework.core.Response;
import cn.net.fusion.framework.enums.HttpCodeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

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
     * 处理全局未知（600）异常
     *
     * @param e Exception
     * @return response
     */
    @ExceptionHandler(Exception.class)
    public Response<Object> globalException(Exception e) {
        logger.error("未知（600）异常 => 原因是：{}", e.getMessage());
        return Response.fail(HttpCodeEnum.RC600.getCode(), e.getMessage());
    }
}
