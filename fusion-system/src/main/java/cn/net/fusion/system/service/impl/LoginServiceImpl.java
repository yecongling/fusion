package cn.net.fusion.system.service.impl;

import cn.net.fusion.framework.constant.CommonConstant;
import cn.net.fusion.framework.core.Response;
import cn.net.fusion.framework.enums.HttpCodeEnum;
import cn.net.fusion.framework.enums.SysOperation;
import cn.net.fusion.framework.event.BaseEvent;
import cn.net.fusion.framework.event.Producer;
import cn.net.fusion.framework.redis.RedisUtil;
import cn.net.fusion.framework.utils.*;
import cn.net.fusion.system.entity.SysUser;
import cn.net.fusion.system.mapper.LoginMapper;
import cn.net.fusion.system.model.SysLoginModel;
import cn.net.fusion.system.service.ILoginService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    // 用于随机选择的字符
    private final String BASE_CHECK_CODES = "abcdefghijklmnopqrstuvwxyzQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    /**
     * 系统登录逻辑
     *
     * @param loginModel 登录模型
     * @return 登录结果，返回给前台的例如token、主页地址等信息
     */
    @Override
    public Response<Object> login(SysLoginModel loginModel) throws Exception {
        String username = loginModel.getUsername();
        // 1、验证用户名是否存在
        SysUser sysUser = loginMapper.getUserByName(username);
        if (sysUser == null) {
            return Response.fail(HttpCodeEnum.RC107.getCode(), HttpCodeEnum.RC107.getMessage());
        }
        // 2、验证登录次数（限制登录次数）
        if (isLoginFailOvertimes(username)) {
            return Response.fail(HttpCodeEnum.RC111.getCode(), HttpCodeEnum.RC111.getMessage());
        }
        // 3、验证码校验
        String captcha = loginModel.getCaptcha();
        if (StringUtils.isBlank(captcha)) {
            return Response.fail(HttpCodeEnum.RC301.getCode(), HttpCodeEnum.RC301.getMessage());
        }
        // 加入验证key
        String lowerCaseCaptcha = captcha.toLowerCase();
        String origin = lowerCaseCaptcha + loginModel.getCheckKey();
        String realKey = MD5Utils.MD5Encode(origin, "utf-8");
        Object checkCode = redisUtil.get(realKey);
        // 验证码验证不过关
        if (checkCode == null || !checkCode.toString().equals(lowerCaseCaptcha)) {
            return Response.fail(HttpCodeEnum.RC300.getCode(), HttpCodeEnum.RC300.getMessage());
        }
        // 4、验证账号可用性（是否冻结）
        if (Objects.equals(sysUser.getStatus(), CommonConstant.USER_FREEZE)) {
            return Response.fail(HttpCodeEnum.RC102.getCode(), HttpCodeEnum.RC102.getMessage());
        }
        // 5、校验密码是否正确（密码错误记录登录失败次数）
        String password = loginModel.getPassword();
        String userPassword = PasswordUtils.encrypt(username, password, sysUser.getSalt());
        if (!userPassword.equals(sysUser.getPassword())) {
            // 密码输入错填，记录登录失败次数
            addLoginFailOvertimes(username);
            return Response.fail(HttpCodeEnum.RC108.getCode(), HttpCodeEnum.RC108.getMessage());
        }
        // 6、7、8步骤接到生成用户相关信息方法
        Response<Object> response = new Response<>();
        generateUserInfo(sysUser, response);
        // 9、登录成功，删除记录的验证码、删除记录的登录失败次数
        redisUtil.delete(realKey);
        redisUtil.delete(CommonConstant.LOGIN_FAIL + username);
        // 10、记录用户登录日志
        BaseEvent<Object> event = new BaseEvent<>(sysUser, SysOperation.LOGIN);
        producer.publishEvent(event);
        return response;
    }

    /**
     * 退出登录
     *
     * @param token 用户token
     * @return 返回退出登录结果
     */
    @Override
    public Object logout(String token) {
        // 获取用户信息
        Object o = redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        // 记录退出登录日志
        BaseEvent<Object> event = new BaseEvent<>(o, SysOperation.LOGOUT);
        producer.publishEvent(event);
        // 清空用户token缓存
        redisUtil.delete(CommonConstant.PREFIX_USER_TOKEN + token);
        return "退出登录成功！";
    }

    /**
     * 生成随机的验证码图像
     *
     * @param key 时间戳
     * @return 图像
     * @throws Exception ex
     */
    @Override
    public String randomImage(String key) throws Exception {
        // 生成随机的四位数的验证码
        String code = RandomUtils.randomString(BASE_CHECK_CODES, 4);
        // 将验证码存储到redis中
        String lowerCode = code.toLowerCase();
        String origin = lowerCode + key;
        String realKey = MD5Utils.MD5Encode(origin, "utf-8");
        redisUtil.set(realKey, origin, 60);
        // 将数据返回给前端
        return RandImageUtils.generate(code);
    }

    /**
     * 生成用户相关信息
     *
     * @param sysUser  用户
     * @param response 结果
     */
    private void generateUserInfo(SysUser sysUser, Response<Object> response) {
        // 6、登录成功，生成token
        String token = UUIDUtils.getUUID();
        // 记录操作员登录地址
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        String localAddr = request.getLocalAddr();
        sysUser.setLoginIp(localAddr);
        // 7、记录token，并记录token有效期(30分钟)
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, sysUser, 1800);
        // 8、获取用户相关信息
        JSONObject result = new JSONObject();
        result.put("token", token);
        result.put("homePath", sysUser.getHomePath());
        result.put("roleId", sysUser.getRoleId());

        response.setCode(HttpCodeEnum.SUCCESS.getCode());
        response.setMessage(HttpCodeEnum.SUCCESS.getMessage());
        response.setData(result);
        response.setStatus(true);
    }

    /**
     * 登录失败次数超过指定次数 返回true （指定次数后续更改为系统参数）
     *
     * @param username 用户名
     * @return true 超过 false 没超过
     */
    private boolean isLoginFailOvertimes(String username) {
        String key = CommonConstant.LOGIN_FAIL + username;
        Object o = redisUtil.get(key);
        if (o != null) {
            int count = Integer.parseInt(o.toString());
            // 这里后续更改为获取系统参数缓存（从redis中获取）
            return count > 5;
        }
        return false;
    }

    /**
     * 记录登录失败次数
     *
     * @param username 用户名
     */
    private void addLoginFailOvertimes(String username) {
        String key = CommonConstant.LOGIN_FAIL + username;
        Object o = redisUtil.get(key);
        int count = 0;
        if (o != null) {
            count = Integer.parseInt(o.toString());
        }
        // 记录次数，有效期10分钟
        redisUtil.set(key, ++count, 600);
    }
}
