package cn.net.fusion.system.controller;

import cn.net.fusion.framework.core.Response;
import cn.net.fusion.framework.enums.HttpCodeEnum;
import cn.net.fusion.system.entity.SysRole;
import cn.net.fusion.system.entity.SysUser;
import cn.net.fusion.system.service.ISysRoleService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysRoleController
 * @Description 系统角色controller
 * @Author ycl
 * @Date 2024/12/26 13:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    private final ISysRoleService sysRoleService;

    @Autowired
    public SysRoleController(ISysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    /**
     * 查询所有的系统角色
     *
     * @param sysRole 查询条件
     * @return 所有的系统角色
     */
    @PostMapping("/getRoleList")
    List<SysRole> getRoleList(@RequestBody(required = false) SysRole sysRole) {
        return sysRoleService.selectRoleList(sysRole);
    }

    /**
     * 新增角色
     *
     * @param sysRole       角色数据
     * @param bindingResult 校验结果
     * @return 新增结果
     */
    @PostMapping("/addRole")
    Response<Integer> addRole(@RequestBody @Valid SysRole sysRole, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        return Response.success(sysRoleService.insertRole(sysRole));
    }

    /**
     * 校验角色编码的唯一性
     *
     * @param roleCode 角色编码
     * @return boolean
     */
    @GetMapping("/checkRoleCodeExist")
    Response<Boolean> checkRoleCodeExist(@RequestParam String roleCode) {
        return Response.success(sysRoleService.checkRoleCodeExist(roleCode));
    }

    /**
     * 编辑角色
     *
     * @param sysRole       角色数据
     * @param bindingResult 校验结果
     * @return 编辑结果
     */
    @PostMapping("/editRole")
    Response<Integer> editRole(@RequestBody @Valid SysRole sysRole, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        return Response.success(sysRoleService.updateRole(sysRole));
    }

    /**
     * 改变角色状态
     *
     * @param sysRole 角色信息 包含角色id和status
     * @return 结果
     */
    @PatchMapping("/changeStatus")
    Response<Integer> changeRoleStatus(@RequestBody Map<String, Object> sysRole) {
        return null;
    }

    /**
     * 获取所有菜单和角色对应选中的菜单
     *
     * @param roleId 角色
     * @return 包含所有菜单和选中的菜单的id
     */
    @GetMapping("/getRoleMenu")
    Response<JSONObject> getRoleMenu(@RequestParam String roleId) {
        return Response.success(sysRoleService.getRoleMenu(roleId));
    }

    /**
     * 给角色分配菜单权限
     *
     * @param params 参数，包含角色编码和对应的菜单编码
     * @return 结果
     */
    @PostMapping("/assignRoleMenu")
    Response<Boolean> assignRoleMenu(@RequestBody JSONObject params) {
        return Response.success(sysRoleService.assignRoleMenu(params.getString("roleId"), params.getJSONArray("menuIds").toList(String.class)));
    }

    /**
     * 根据角色获取用户
     *
     * @param roleId 角色编码
     * @return 用户信息
     */
    @GetMapping("/getRoleUser")
    Response<SysUser> getRoleUser(@RequestParam String roleId) {
        return null;
    }
}
