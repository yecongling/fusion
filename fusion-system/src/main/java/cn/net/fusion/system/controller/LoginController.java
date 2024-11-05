package cn.net.fusion.system.controller;

import cn.net.fusion.framework.core.Response;
import cn.net.fusion.system.model.SysLoginModel;
import cn.net.fusion.system.service.ILoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description 登录控制器入口
 * @Author 叶丛林
 * @Date 2024/11/4 21:03
 * @Version 1.0
 **/
@RestController
public class LoginController {

    // 登录操作业务接口
    private final ILoginService loginService;

    @Autowired
    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 系统登录操作接口
     *
     * @param loginModel    登录的用户信息
     * @param bindingResult Validation的校验错误存放对象
     * @return 返回登录结果
     */
    @PostMapping("/login")
    public Response<Object> login(@RequestBody @Valid SysLoginModel loginModel, BindingResult bindingResult) {

        return null;
    }
}
