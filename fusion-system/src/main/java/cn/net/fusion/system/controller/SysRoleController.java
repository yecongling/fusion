package cn.net.fusion.system.controller;

import cn.net.fusion.framework.core.Response;
import cn.net.fusion.framework.enums.HttpCodeEnum;
import cn.net.fusion.system.entity.SysRole;
import cn.net.fusion.system.service.ISysRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return Response.success(sysRoleService.insertRole(sysRole));
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
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return null;
    }
}
