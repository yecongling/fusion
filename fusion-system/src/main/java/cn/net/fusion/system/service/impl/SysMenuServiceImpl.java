package cn.net.fusion.system.service.impl;

import cn.net.fusion.framework.core.Response;
import cn.net.fusion.system.entity.SysMenu;
import cn.net.fusion.system.mapper.SysMenuMapper;
import cn.net.fusion.system.service.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysMenuServiceImpl
 * @Description 系统菜单业务实现类
 * @Author ycl
 * @Date 2024/11/11 14:46
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    private final SysMenuMapper sysMenuMapper;

    @Autowired
    public SysMenuServiceImpl(SysMenuMapper sysMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
    }

    /**
     * 获取所有菜单
     *
     * @param menu 菜单查询条件
     * @return 符合条件的菜单
     */
    @Override
    public List<SysMenu> getAllMenus(SysMenu menu) {
        List<SysMenu> allMenus = sysMenuMapper.getAllMenus(menu);
        // 构建上下级关系的树结构数据
        return this.buildMenus(allMenus);
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

    /**
     * 构建菜单的上下级关系
     *
     * @param menus 查询的平级的菜单数据
     * @return 构建成树结构的菜单数据
     */
    private List<SysMenu> buildMenus(List<SysMenu> menus) {
        // 做id和菜单的映射，方便后续查找父级菜单
        Map<String, SysMenu> idToMenuMap = new HashMap<>();
        for (SysMenu menu : menus) {
            idToMenuMap.put(menu.getId(), menu);
        }
        List<SysMenu> root = new ArrayList<>();
        for (SysMenu menu : menus) {
            String parentId = menu.getParentId();
            if (StringUtils.isBlank(parentId)) {
                root.add(menu);
            } else {
                SysMenu parent = idToMenuMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(menu);
                } else {
                    // 如果配置了父级但是没有显示父级的都按一级菜单处理
                    root.add(menu);
                }
            }
        }
        return root;
    }
}
