package cn.net.fusion.system.mapper;

import cn.net.fusion.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SysMenuMapper
 * @Description 系统菜单的mapper
 * @Author ycl
 * @Date 2024/11/8 15:31
 * @Version 1.0
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

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
}
