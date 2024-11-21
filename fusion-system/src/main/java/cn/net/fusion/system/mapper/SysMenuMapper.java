package cn.net.fusion.system.mapper;

import cn.net.fusion.system.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysMenuMapper
 * @Description 系统菜单的mapper
 * @Author ycl
 * @Date 2024/11/8 15:31
 * @Version 1.0
 */
public interface SysMenuMapper {

    /**
     * 获取所有菜单
     *
     * @param menu 菜单查询条件
     * @return 符合条件的菜单
     */
    List<SysMenu> getAllMenus(@Param("menu") SysMenu menu);

    /**
     * 查询用户菜单，是根据用户所处的角色
     *
     * @param roleId 角色ID
     * @return 用户权限
     */
    List<SysMenu> queryByUser(@Param("roleId") String roleId);

    /**
     * 查询目录 - 一级菜单
     *
     * @return 一级菜单
     */
    List<SysMenu> getDirectory();

    /**
     * 添加菜单
     *
     * @param menu 菜单对象
     * @return 受影响的行数
     */
    int addMenu(SysMenu menu);

    /**
     * 更新菜单
     *
     * @param map 菜单对象数据
     * @return -
     */
    int updateMenu(Map<String, Object> map);

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return -
     */
    int deleteMenu(String id);
}
