package cn.fusion.framework.interceptor;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MvcInterceptor
 * @Description 请求拦截器，用于拦截用户请求，判定是否进行了登录，未登录的用户不能进行后台资源的访问
 * @Author 叶丛林
 * @Date 2024/11/2 10:15
 * @Version 1.0
 **/
@Configuration
public class MvcInterceptor implements WebMvcConfigurer {

    private final RateLimitingInterceptor rateLimitingInterceptor;

    @Autowired
    public MvcInterceptor(RateLimitingInterceptor rateLimitingInterceptor) {
        this.rateLimitingInterceptor = rateLimitingInterceptor;
    }

    /**
     * 添加拦截器
     *
     * @param registry 拦截器配置中心
     */
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加登录验证拦截器，但是需要排除一些拦截登录验证的路径(登录地址、退出登录地址、获取验证码地址不需要验证)
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin())).addPathPatterns("/**").excludePathPatterns("/login", "/logout", "/getCaptcha/*");
        // 限流
        registry.addInterceptor(rateLimitingInterceptor).addPathPatterns("/**");
    }

    /**
     * 开启跨域支持
     *
     * @param registry 跨域配置中心
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false).exposedHeaders("Authorization");
    }
}
