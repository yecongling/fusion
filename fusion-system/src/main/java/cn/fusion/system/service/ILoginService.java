package cn.fusion.system.service;

import cn.fusion.framework.core.Response;
import cn.fusion.system.model.SysLoginModel;

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
     * @param loginModel 登录模型
     * @return 登录结果，返回给前台的例如token、主页地址等信息
     */
    Response<Object> login(SysLoginModel loginModel) throws Exception;

    /**
     * 退出登录
     *
     * @return 返回退出登录结果
     */
    Object logout();

    /**
     * 生成随机的验证码图像
     *
     * @param key 时间戳
     * @return 图像
     * @throws Exception ex
     */
    String randomImage(String key) throws Exception;
}
