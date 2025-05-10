package cn.fusion.framework.resolver;

import cn.fusion.framework.annotation.CurrentUser;
import cn.fusion.framework.core.SysOpr;
import cn.fusion.framework.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @ClassName CurrentUserArgumentResolver
 * @Description 当前操作人解析器
 * @Author ycl
 * @Date 2025/5/10 11:37
 * @Version 1.0
 **/
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final ServletUtils servletUtils;

    @Autowired
    public CurrentUserArgumentResolver(ServletUtils servletUtils) {
        this.servletUtils = servletUtils;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class) && parameter.getParameterType().equals(SysOpr.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 这里面去处理了当前请求的信息
        return servletUtils.getSysOpr();
    }
}
