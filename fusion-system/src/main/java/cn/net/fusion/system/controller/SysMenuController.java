package cn.net.fusion.system.controller;

import cn.net.fusion.framework.core.Response;
import cn.net.fusion.system.entity.SysMenu;
import cn.net.fusion.system.service.ISysMenuService;
import com.alibaba.fastjson2.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysMenuController
 * @Description 系统菜单管理controller
 * @Author ycl
 * @Date 2024/11/8 11:57
 * @Version 1.0
 */
@RequestMapping("/system/menu")
@RestController
public class SysMenuController {

    private final ISysMenuService sysMenuService;

    @Autowired
    public SysMenuController(ISysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    /**
     * 查询所有菜单
     *
     * @param sysMenu 查询条件
     * @return 符合条件的菜单
     */
    @PostMapping("/getAllMenus")
    public List<SysMenu> getAllMenu(@RequestBody(required = false) SysMenu sysMenu) {
        return sysMenuService.getAllMenus(sysMenu);
    }

    /**
     * 根据角色查询对应的菜单
     *
     * @param roleId 角色id
     * @return 菜单信息
     */
    @GetMapping("/getMenusByRole")
    public JSONArray getMenusByRoleId(@RequestParam(name = "roleId") String roleId) {
        return sysMenuService.getMenusByRoleId(roleId);
    }

    /**
     * 获取所有的菜单目录（非子菜单）
     *
     * @return 菜单目录
     */
    @GetMapping("/getDirectory")
    public JSONArray getMenuDirectory() {
        return sysMenuService.getDirectory();
    }

    /**
     * 新增菜单
     *
     * @param sysMenu 系统菜单
     * @return 新增结果
     */
    @PostMapping("/addMenu")
    public Response<SysMenu> addMenu(@RequestBody SysMenu sysMenu) {
        return null;
    }

    /**
     * 修改菜单
     *
     * @param sysMenu 系统菜单
     * @return 修改结果
     */
    @PostMapping("/modifyMenu")
    public Response<SysMenu> modifyMenu(@RequestBody SysMenu sysMenu) {
        return null;
    }

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return 删除结果
     */
    @DeleteMapping("/deleteMenu")
    public Response<SysMenu> deleteMenu(@RequestParam(name = "id") String id) {
        return null;
    }
}
