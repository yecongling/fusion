package cn.fusion.framework.interceptor;

import cn.fusion.framework.security.DecryptedHttpInputMessage;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Type;

/**
 * @ClassName DecryptRequestBodyAdvice
 * @Description 后端解密（使用了@requestBody注解标明的参数都是需要走这里来解密的）
 * @Author ycl
 * @Date 2024/11/4 10:24
 * @Version 1.0
 */
@ControllerAdvice
@Component
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    /**
     * 表示支持哪些方法
     *
     * @param methodParameter 方法参数
     * @param targetType      目标类型
     * @param converterType   转换类型
     * @return true|false
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 在请求读body数据前进行数据解密
     *
     * @param inputMessage  输入信息
     * @param parameter     参数
     * @param targetType    目标类型
     * @param converterType 转换类型
     * @return -
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 获取请求头中的加密判定，决定是否需要进行解密操作
        HttpHeaders headers = inputMessage.getHeaders();
        // 不需要解密
        if (!"1".equals(headers.getFirst("X-Encrypted"))) {
            return inputMessage;
        }
        try {
            return new DecryptedHttpInputMessage(inputMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 返回解密的请求体
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
