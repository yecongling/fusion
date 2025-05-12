package cn.fusion.system.controller;

import cn.fusion.framework.core.Response;
import cn.fusion.system.entity.SysMenu;
import cn.fusion.system.service.ISysMenuService;
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
    public JSONArray getMenusByRoleId(@RequestParam(name = "roleId") Long roleId) {
        return sysMenuService.getMenusByRoleId(roleId);
    }

    /**
     * 获取所有的菜单目录，针对按钮则需要查所有目录
     *
     * @return 菜单目录
     */
    @GetMapping("/getDirectory")
    public JSONArray getMenuDirectory(@RequestParam(name = "menuType") int menuType) {
        return sysMenuService.getDirectory(menuType);
    }

    /**
     * 新增菜单
     *
     * @param sysMenu 系统菜单
     * @return 新增结果
     */
    @PostMapping("/addMenu")
    public Response<Integer> addMenu(@RequestBody SysMenu sysMenu) {
        return sysMenuService.addMenu(sysMenu);
    }

    /**
     * 修改菜单（使用map的原因是根据数目动态更新内部的字段）
     *
     * @param menuMap 菜单数据
     * @return 修改结果
     */
    @PostMapping("/updateMenu")
    public Response<Integer> updateMenu(@RequestBody SysMenu menuMap) {
        return sysMenuService.modifyMenu(menuMap);
    }

    /**
     * 删除菜单（假删除 - 更改del_flag）
     *
     * @param id 菜单id
     * @return 删除结果
     */
    @DeleteMapping("/deleteMenu")
    public Response<Integer> deleteMenu(@RequestParam(name = "id") Long id) {
        return sysMenuService.deleteMenu(id);
    }

    /**
     * 批量删除菜单（假删除 - 更改del_flag）
     *
     * @param menusId 菜单id
     * @return 删除结果
     */
    @DeleteMapping("/deleteMenuBatch")
    public Response<Boolean> deleteMenuBatch(@RequestBody List<Long> menusId) {
        Boolean b = sysMenuService.deleteBatch(menusId);
        return b ? Response.success() : Response.fail();
    }
}
