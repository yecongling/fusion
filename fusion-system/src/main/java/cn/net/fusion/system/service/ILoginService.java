package cn.net.fusion.system.service;

import cn.net.fusion.system.model.SysUser;

import java.util.Map;

/**
 * @ClassName ILoginService
 * @Description 系统登录业务接口
 * @Author 叶丛林
 * @Date 2024/11/4 21:04
 * @Version 1.0
 **/
public interface ILoginService {

    /**
     * 系统登录逻辑
     *
     * @param user 用户信息
     * @return 登录结果，返回给前台的例如token、主页地址等信息
     */
    Map<String, Object> login(SysUser user);

    /**
     * 退出登录
     *
     * @param token 用户token
     * @return 返回退出登录结果
     */
    Object logout(String token);
}
