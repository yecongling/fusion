package cn.fusion.system.controller;

import cn.fusion.framework.core.Response;
import cn.fusion.framework.enums.HttpCodeEnum;
import cn.fusion.system.model.SysLoginModel;
import cn.fusion.system.service.ILoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public Response<Object> login(@RequestBody @Valid SysLoginModel loginModel, BindingResult bindingResult) throws Exception {
        // 如果校验有误，直接返回参数错误信息
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        // 调用业务接口
        return loginService.login(loginModel);
    }

    /**
     * 退出登录
     *
     * @return 结果
     */
    @RequestMapping("/logout")
    public Response<Object> logout() {
        return Response.success(loginService.logout());
    }

    /**
     * 获取验证码
     *
     * @param key 拼接key，前台传过来的时间戳
     * @return 验证码图像
     */
    @GetMapping("/getCaptcha/{key}")
    public Response<String> getCaptcha(@PathVariable("key") String key) throws Exception {
        return Response.success(loginService.randomImage(key));
    }
}
