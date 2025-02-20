package cn.fusion.system.service;

import cn.fusion.framework.core.Response;
import cn.fusion.system.entity.SysMenu;
import com.alibaba.fastjson2.JSONArray;

import java.util.List;

/**
 * @ClassName ISysMenuService
 * @Description 系统菜单业务接口
 * @Author ycl
 * @Date 2024/11/8 15:31
 * @Version 1.0
 */
public interface ISysMenuService {

    /**
     * 获取所有菜单
     *
     * @param menu 菜单查询条件
     * @return 符合条件的菜单
     */
    List<SysMenu> getAllMenus(SysMenu menu);

    /**
     * 根据角色获取菜单
     *
     * @param roleId 角色id
     * @return 菜单信息
     */
    JSONArray getMenusByRoleId(String roleId);

    /**
     * 获取一级菜单，用于构建菜单的上级
     *
     * @return 目录
     */
    JSONArray getDirectory();

    /**
     * 新增菜单
     *
     * @param menu 菜单
     * @return 结果
     */
    Response<Integer> addMenu(SysMenu menu);

    /**
     * 更新菜单
     *
     * @param menu 菜单对象
     * @return 结果
     */
    Response<Integer> modifyMenu(SysMenu menu);

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return -
     */
    Response<Integer> deleteMenu(String id);

    /**
     * 批量删除
     *
     * @param ids 菜单id
     * @return true 删除成功 false 删除失败
     */
    Boolean deleteBatch(List<String> ids);
}
