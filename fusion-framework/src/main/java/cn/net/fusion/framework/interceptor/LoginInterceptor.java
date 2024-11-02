package cn.net.fusion.framework.interceptor;

import cn.net.fusion.framework.core.Response;
import cn.net.fusion.framework.enums.HttpCodeEnum;
import cn.net.fusion.framework.redis.RedisUtil;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @ClassName LoginInterceptor
 * @Description 自定义登录拦截器，用于验证用户登录权限
 * @Author 叶丛林
 * @Date 2024/11/2 21:24
 * @Version 1.0
 **/
public class LoginInterceptor implements HandlerInterceptor {

    // redis工具（用于验证用户的登录token）
    private final RedisUtil redisUtil;

    public LoginInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 处理前的验证
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // 判断request的header中是否携带token，根据token判定用户是否登录且在有效期
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            response.getWriter().write(JSONObject.toJSONString(Response.fail(HttpCodeEnum.RC101.getCode(), HttpCodeEnum.RC101.getMessage())));
            return false;
        }
        // 从redis中获取用户的登录信息，判定token有效
        if (!redisUtil.hasKey(token)) {
            response.getWriter().write(JSONObject.toJSONString(Response.fail(HttpCodeEnum.RC100.getCode(), HttpCodeEnum.RC100.getMessage())));
            return false;
        }
        return true;
    }
}
