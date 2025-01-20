package cn.net.fusion.framework.interceptor;

import cn.net.fusion.framework.enums.HttpCodeEnum;
import cn.net.fusion.framework.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @ClassName RateLimitingInterceptor
 * @Description 流量拦截器
 * @Author 叶丛林
 * @Date 2025/1/20 22:06
 * @Version 1.0
 **/
@Component
public class RateLimitingInterceptor implements HandlerInterceptor {

    /**
     * 限流service
     */
    private final RateLimiterService rateLimiterService;

    @Autowired
    public RateLimitingInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 使用请求的IP进行限流
        String IP = request.getRemoteAddr();
        // 判断是否超出限流
        boolean allowed = rateLimiterService.tryConsume(IP);
        if (!allowed) {
            response.setStatus(HttpCodeEnum.RC429.getCode());
            response.getWriter().write(HttpCodeEnum.RC429.getMessage());
            return false;
        }
        // 继续执行请求
        return true;
    }
}
