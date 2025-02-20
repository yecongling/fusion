package cn.fusion.framework.interceptor;

import cn.fusion.framework.core.Response;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName ResponseInterceptor
 * @Description 统一返回数据格式
 * @Author ycl
 * @Date 2024/11/4 10:19
 * @Version 1.0
 */
@ControllerAdvice
public class ResponseInterceptor implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Response) {
            return body;
        }
        // 保证返回的数据是统一格式
        return Response.success(body);
    }
}
