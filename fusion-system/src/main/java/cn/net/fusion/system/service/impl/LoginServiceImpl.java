package cn.net.fusion.system.service.impl;

import cn.net.fusion.framework.event.Producer;
import cn.net.fusion.framework.redis.RedisUtil;
import cn.net.fusion.system.mapper.LoginMapper;
import cn.net.fusion.system.model.SysUser;
import cn.net.fusion.system.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName LoginServiceImpl
 * @Description 登录逻辑的业务实现类
 * @Author 叶丛林
 * @Date 2024/11/4 21:07
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements ILoginService {

    private final LoginMapper loginMapper;

    // redis操作
    private final RedisUtil redisUtil;

    // 消息生产者
    private final Producer<Object> producer;

    @Autowired
    public LoginServiceImpl(LoginMapper loginMapper, RedisUtil redisUtil, Producer<Object> producer) {
        this.loginMapper = loginMapper;
        this.redisUtil = redisUtil;
        this.producer = producer;
    }

    /**
     * 系统登录逻辑
     *
     * @param user 用户信息
     * @return 登录结果，返回给前台的例如token、主页地址等信息
     */
    @Override
    public Map<String, Object> login(SysUser user) {

        return Map.of();
    }

    /**
     * 退出登录
     *
     * @param token 用户token
     * @return 返回退出登录结果
     */
    @Override
    public Object logout(String token) {
        return null;
    }
}
