package cn.net.fusion.system.controller;

import cn.net.fusion.system.service.ISysUserService;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysUserController
 * @Description 系统用户controller
 * @Author ycl
 * @Date 2024/12/26 13:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    private final ISysUserService sysUserService;

    @Autowired
    public SysUserController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 查询用户信息
     *
     * @param params 用户检索条件
     * @return {用户集合、分页参数}
     */
    @PostMapping("/queryUserList")
    public JSONObject queryUserList(@RequestBody JSONObject params) {
        // 获取分页参数
        int pageNum = params.getIntValue("pageNum");
        int pageSize = params.getIntValue("pageSize");
        // 其他查询条件
        return sysUserService.getAllUser(pageNum, pageSize, params.getJSONObject("searchParams"));
    }
}
