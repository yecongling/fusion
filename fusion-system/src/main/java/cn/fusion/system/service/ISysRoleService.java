package cn.fusion.system.service;

import cn.fusion.system.entity.SysRole;
import com.alibaba.fastjson2.JSONObject;

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
     * @param params 系统角色
     * @return 符合条件的系统角色
     */
    JSONObject getAllRoleList(JSONObject params);

    /**
     * 插入角色
     *
     * @param sysRole 角色数据
     * @return 受影响的行数
     */
    Integer insertRole(SysRole sysRole);

    /**
     * 检测角色编码是否存在
     *
     * @param roleCode 角色编码
     * @return true 存在 false不存在
     */
    Boolean checkRoleCodeExist(String roleCode);

    /**
     * 更新角色
     *
     * @param sysRole 系统角色
     * @return 受影响的行数
     */
    Integer updateRole(SysRole sysRole);

    /**
     * 获取所有菜单和角色对应选中的菜单
     *
     * @param roleId 角色id
     * @return 所有菜单和角色配置的菜单
     */
    JSONObject getRoleMenu(Long roleId);

    /**
     * 根据角色获取该角色下的用户
     * @param roleId 角色id
     * @param params 查询参数（包括分页条件）
     * @return 用户信息
     */
    JSONObject getRoleUser(Long roleId, JSONObject params);

    /**
     * 分页查询不在当前角色下的用户
     * @param roleId 角色ID
     * @param params 查询参数（包括分页条件）
     * @return 数据（包含用户数据、分页数据）
     */
    JSONObject getUserNotInRoleByPage(Long roleId, JSONObject params);

    /**
     * 给角色分配菜单权限
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID
     * @return 分配结果
     */
    Boolean assignRoleMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配用户
     *
     * @param roleId 角色ID
     * @param operate 操作类型（add 添加 delete 删除（单个删除和批量删除））
     * @param userIds 所有的用户ID
     * @return 分配结果
     */
    boolean assignRoleUser(Long roleId, String operate, List<Long> userIds);
}
