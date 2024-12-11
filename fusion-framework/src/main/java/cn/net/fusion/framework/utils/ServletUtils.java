package cn.net.fusion.framework.utils;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.net.fusion.framework.constant.CommonConstant;
import cn.net.fusion.framework.core.SysOpr;
import cn.net.fusion.framework.enums.HttpCodeEnum;
import cn.net.fusion.framework.exception.BusinessException;
import cn.net.fusion.framework.redis.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServletUtils
 * @Description 获取当前请求上下文中的操作员 可通过这种方式获取
 * @Author 叶丛林
 * @Date 2024/11/4 20:27
 * @Version 1.0
 **/
@Component
public class ServletUtils {
    // redis操作的工具类
    private RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 获取当前请求下的操作员
     *
     * @return 系统操作员
     */
    public SysOpr getSysOpr() {
        // 从request中获取token，然后在redis中获取该操作员
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 如果是登录地址进来，这里是获取不到操作员的（这里是mybatis拦截器那里可能会拦截登录的sql取操作员）
        String requestURI = request.getRequestURI();
        // 这里考虑如果是登录的话是否将认定为系统操作
        if (requestURI.endsWith("/login")) {
            return new SysOpr();
        }
        Object o = redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + tokenInfo);
        if (ObjectUtils.isEmpty(o)) {
            // 如果没取到，直接抛出异常，阻止进行下一步操作
            throw new BusinessException(HttpCodeEnum.RC401);
        }
        return (SysOpr) o;
    }

}
