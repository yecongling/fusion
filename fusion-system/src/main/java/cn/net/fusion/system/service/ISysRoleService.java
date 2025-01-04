package cn.net.fusion.system.service;

import cn.net.fusion.system.entity.SysRole;

import java.util.List;

/**
 * @ClassName ISysRoleService
 * @Description 系统角色service接口
 * @Author ycl
 * @Date 2024/12/26 13:45
 * @Version 1.0
 */
public interface ISysRoleService {

    /**
     * 查询所有角色
     *
     * @param sysRole 系统角色
     * @return 符合条件的系统角色
     */
    List<SysRole> selectRoleList(SysRole sysRole);

    /**
     * 插入角色
     *
     * @param sysRole 角色数据
     * @return 受影响的行数
     */
    Integer insertRole(SysRole sysRole);

    /**
     * 更新角色
     *
     * @param sysRole 系统角色
     * @return 受影响的行数
     */
    Integer updateRole(SysRole sysRole);
}
