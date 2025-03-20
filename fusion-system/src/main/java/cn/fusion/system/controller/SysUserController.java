
package cn.fusion.system.controller;

import cn.fusion.framework.core.Response;
import cn.fusion.framework.enums.HttpCodeEnum;
import cn.fusion.system.entity.SysUser;
import cn.fusion.system.service.ISysUserService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 获取回收站的用户，分页查询
     *
     * @param params 用户检索条件
     * @return {用户集合、分页参数}
     */
    @PostMapping("/getUserListRecycle")
    public JSONObject getUserListRecycle(@RequestBody JSONObject params) {
        int pageNum = params.getIntValue("pageNum");
        int pageSize = params.getIntValue("pageSize");
        return sysUserService.getUserListRecycle(pageNum, pageSize, params.getJSONObject("searchParams"));
    }

    /**
     * 校验用户名唯一
     *
     * @param userName 用户名
     * @return true 唯一 false 不唯一
     */
    @GetMapping("/checkUserNameUnique/{userName}")
    public boolean checkUserNameUnique(@PathVariable String userName) {
        return sysUserService.checkUserNameUnique(userName);
    }

    /**
     * 添加用户
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @PostMapping("/addUser")
    public Response<Boolean> addUser(@RequestBody @Valid SysUser sysUser, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        return Response.success(sysUserService.addUser(sysUser));
    }

    /**
     * 更新用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @PostMapping("/updateUser")
    public boolean updateUser(@RequestBody SysUser sysUser) {
        return sysUserService.updateUser(sysUser);
    }

    /**
     * 批量删除用户信息（逻辑删除-放入回收站）
     *
     * @param userIds 用户ID集合
     * @return true｜false
     */
    @PostMapping("/logicDeleteUsers")
    public boolean logicDeleteBatchUser(@RequestBody List<Long> userIds) {
        return sysUserService.logicDeleteBatchUser(userIds);
    }

    /**
     * 批量删除用户（物理删除）
     *
     * @param userIds 用户ID
     * @return 结果
     */
    @PostMapping("/deleteUsers")
    public boolean physicalDeleteBatchUsers(@RequestBody List<Long> userIds) {
        return sysUserService.physicalDeleteBatchUsers(userIds);
    }

    /**
     * 从回收站批量恢复用户
     *
     * @param userIds 用户ID
     * @return 恢复结果
     */
    @PostMapping("/recoverFromRecycle")
    public boolean recoverFromRecycle(@RequestBody List<Long> userIds) {
        return sysUserService.recoverFromRecycle(userIds);
    }

    /**
     * 批量锁定用户
     *
     * @param userIds 用户ID
     * @return 锁定结果
     */
    @PostMapping("/lockBatchUser")
    public boolean lockBatchUser(@RequestBody List<Long> userIds) {
        return sysUserService.lockBatchUser(userIds);
    }

    /**
     * 批量解锁用户
     *
     * @param userIds 用户ID
     * @return true ｜ false
     */
    @PostMapping("/unlockBatchUser")
    public boolean unlockBatchUser(@RequestBody List<Long> userIds) {
        return sysUserService.unlockBatchUser(userIds);
    }

    /**
     * 重置用户密码(重置为123456)
     *
     * @param userId 用户ID
     * @return true | false
     */
    @PostMapping("/resetPwd/{userId}")
    public boolean resetPwd(@PathVariable Long userId) throws Exception {
        return sysUserService.resetPwd(userId);
    }

    /**
     * 修改用户密码
     *
     * @param userId 用户ID
     * @param newPwd 新密码
     * @return true| false
     */
    @PostMapping("/modifyPwd/{userId}")
    public boolean modifyPwd(@PathVariable Long userId, @RequestParam String newPwd) throws Exception {
        return sysUserService.modifyPwd(userId, newPwd);
    }
}

