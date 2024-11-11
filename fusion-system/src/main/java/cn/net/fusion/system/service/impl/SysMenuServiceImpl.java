package cn.net.fusion.system.service.impl;

import cn.net.fusion.framework.core.Response;
import cn.net.fusion.system.entity.SysMenu;
import cn.net.fusion.system.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysMenuServiceImpl
 * @Description 系统菜单业务实现类
 * @Author ycl
 * @Date 2024/11/11 14:46
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    /**
     * 获取所有菜单
     *
     * @param menu 菜单查询条件
     * @return 符合条件的菜单
     */
    @Override
    public List<SysMenu> getAllMenus(SysMenu menu) {
        return List.of();
    }

    /**
     * 根据角色获取菜单
     *
     * @param roleId 角色id
     * @return 菜单信息
     */
    @Override
    public List<SysMenu> getMenusByRoleId(String roleId) {
        return List.of();
    }

    /**
     * 获取一级菜单，用于构建菜单的上级
     *
     * @return 目录
     */
    @Override
    public List<SysMenu> getDirectory() {
        return List.of();
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单
     * @return 结果
     */
    @Override
    public Response<SysMenu> addMenu(SysMenu menu) {
        return null;
    }

    /**
     * 更新菜单
     *
     * @param menu 菜单对象
     * @return 结果
     */
    @Override
    public Response<SysMenu> modifyMenu(SysMenu menu) throws Exception {
        return null;
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return -
     */
    @Override
    public Response<SysMenu> deleteMenu(String id) {
        return null;
    }
}
