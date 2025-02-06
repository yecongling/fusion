package cn.net.fusion.system.service.impl;

import cn.net.fusion.framework.core.SysOpr;
import cn.net.fusion.framework.utils.ServletUtils;
import cn.net.fusion.system.entity.*;
import cn.net.fusion.system.mapper.SysMenuMapper;
import cn.net.fusion.system.mapper.SysRoleMapper;
import cn.net.fusion.system.mapper.SysRoleMenuMapper;
import cn.net.fusion.system.mapper.SysUserRoleMapper;
import cn.net.fusion.system.service.ISysRoleService;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName SysRoleServiceImpl
 * @Description 系统角色service实现
 * @Author ycl
 * @Date 2025/1/4 10:05
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleMapper sysRoleMapper;

    private final SysMenuMapper sysMenuMapper;

    private final SysRoleMenuMapper sysRoleMenuMapper;

    private final SysUserRoleMapper sysUserRoleMapper;

    private final ServletUtils servletUtils;

    @Autowired
    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper, SysMenuMapper sysMenuMapper,
                              SysRoleMenuMapper sysRoleMenuMapper, ServletUtils servletUtils,
                              SysUserRoleMapper sysUserRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysMenuMapper = sysMenuMapper;
        this.sysRoleMenuMapper = sysRoleMenuMapper;
        this.servletUtils = servletUtils;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    /**
     * 查询所有角色
     *
     * @param sysRole 系统角色
     * @return 符合条件的系统角色
     */
    @Override
    public List<SysRole> selectRoleList(SysRole sysRole) {
        QueryWrapper queryWrapper = new QueryWrapper();
        // 根据角色名称、角色编码、状态进行查询
        queryWrapper.like(SysRole::getRoleName, sysRole.getRoleName());
        queryWrapper.like(SysRole::getRoleCode, sysRole.getRoleCode());
        queryWrapper.eq(SysRole::getStatus, sysRole.getStatus());
        // 选择需要的字段
        queryWrapper.select(
                QueryMethods.column(SysRole::getId),
                QueryMethods.column(SysRole::getRoleName),
                QueryMethods.column(SysRole::getRoleCode),
                QueryMethods.column(SysRole::getStatus),
                QueryMethods.column(SysRole::getRoleType),
                QueryMethods.column(SysRole::getRemark)
        );
        return sysRoleMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 插入角色
     *
     * @param sysRole 角色数据
     * @return 受影响的行数
     */
    @Override
    public Integer insertRole(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }

    /**
     * 检测角色编码是否存在
     *
     * @param roleCode 角色编码
     * @return true 存在 false不存在
     */
    @Override
    public Boolean checkRoleCodeExist(String roleCode) {
        QueryWrapper wrapper = new QueryWrapper().eq(SysRole::getRoleCode, roleCode);
        long l = sysRoleMapper.selectCountByQuery(wrapper);
        return l > 0;
    }

    /**
     * 更新角色
     *
     * @param sysRole 系统角色
     * @return 受影响的行数
     */
    @Override
    public Integer updateRole(SysRole sysRole) {
        return sysRoleMapper.update(sysRole);
    }

    /**
     * 获取所有菜单和角色对应选中的菜单
     *
     * @param roleId 角色id
     * @return 所有菜单和角色配置的菜单
     */
    @Override
    public JSONObject getRoleMenu(String roleId) {
        JSONObject jsonObject = new JSONObject();
        // 查询所有的菜单数据
        List<SysMenu> menuList = sysMenuMapper.selectAll();
        // 将menuList构建成树结构
        List<SysMenu> sysMenus = this.buildMenus(menuList);
        jsonObject.put("menuList", sysMenus);
        // 查询角色分配的菜单权限
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectListByQuery(queryWrapper);
        List<String> menuIds = new ArrayList<>();
        sysRoleMenus.forEach(sysRoleMenu -> {
            menuIds.add(sysRoleMenu.getMenuId());
        });
        jsonObject.put("menuIds", menuIds);
        return jsonObject;
    }

    /**
     * 根据角色获取该角色下的用户
     *
     * @param roleId 角色id
     * @return 用户信息
     */
    @Override
    public JSONObject getRoleUser(String roleId, int pageNum, int pageSize, JSONObject params) {
        QueryWrapper queryWrapper = new QueryWrapper();
        JSONObject result = new JSONObject();
        // 需要的字段
        queryWrapper.select(
                        QueryMethods.column(SysUserRole::getId),
                        QueryMethods.column(SysUser::getId).as(SysUserRole::getUserId),
                        QueryMethods.column(SysUser::getUsername),
                        QueryMethods.column(SysUser::getRealName),
                        QueryMethods.column(SysUser::getSex)
                )
                // 查询用户角色表
                .from(SysUserRole.class).as("role")
                // 关联用户表
                .leftJoin(SysUser.class).as("user")
                .on(SysUserRole::getUserId, SysUser::getId)
                // 查询条件
                .eq(SysUserRole::getRoleId, roleId);
        // 分页查询
        return queryPage(pageNum, pageSize, queryWrapper, result, params);
    }

    /**
     * 分页查询
     *
     * @param pageNum      页码
     * @param pageSize     页大小
     * @param queryWrapper 查询条件
     * @param result       结果
     * @return 分页数据
     */
    private JSONObject queryPage(int pageNum, int pageSize, QueryWrapper queryWrapper, JSONObject result, JSONObject queryParams) {
        // 处理searchParams为null的情况
        if (queryParams == null) {
            queryParams = new JSONObject();
        }
        // 拼接其他的查询条件
        queryWrapper.eq(SysUser::getUsername, queryParams.getString("username"), StringUtils.isNotBlank(queryParams.getString("realName")));
        queryWrapper.eq(SysUser::getRealName, queryParams.getString("realName"), StringUtils.isNotBlank(queryParams.getString("realName")));
        queryWrapper.eq(SysUser::getSex, queryParams.getIntValue("sex"), StringUtils.isNotBlank(queryParams.getString("sex")));
        Page<SysUserRole> paginate;
        boolean isFirstPage = pageNum == 1;
        // flex totalRow参数，传入小于0的会查询总量， 否则不会查询总量
        paginate = LogicDeleteManager.execWithoutLogicDelete(() -> sysUserRoleMapper.paginate(pageNum, pageSize, isFirstPage ? -1 : 0, queryWrapper));
        // 第一页才返回查询的数据总量
        if (isFirstPage) {
            result.put("total", paginate.getTotalRow());
        }
        result.put("data", paginate.getRecords());
        return result;
    }

    /**
     * 分页查询不在当前角色下的用户
     *
     * @param roleId   角色ID
     * @param pageNum  页码
     * @param pageSize 数量
     * @return 数据（包含用户数据、分页数据）
     */
    @Override
    public JSONObject getUserNotInRoleByPage(String roleId, int pageNum, int pageSize, JSONObject queryParams) {
        JSONObject result = new JSONObject();
        QueryWrapper queryWrapper = new QueryWrapper();
        // 筛选字段
        queryWrapper.select(
                        QueryMethods.column(SysUser::getId),
                        QueryMethods.column(SysUser::getUsername),
                        QueryMethods.column(SysUser::getRealName),
                        QueryMethods.column(SysUser::getSex)
                )
                // 查询用户表数据
                .from(SysUser.class).as("user")
                // 关联用户角色表数据
                .leftJoin(SysUserRole.class).as("role")
                // 条件
                .on(wrapper -> wrapper
                        .and(SysUser::getId).eq(SysUserRole::getUserId)
                        .eq(SysUserRole::getRoleId, roleId)
                )
                .where(SysUserRole::getUserId).isNull();
        // 分页（第一页需要查询总页数，后续不需要）
        return queryPage(pageNum, pageSize, queryWrapper, result, queryParams);
    }

    /**
     * 给角色分配菜单权限
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID
     * @return 分配结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean assignRoleMenu(String roleId, List<String> menuIds) {
        // 当前操作员
        SysOpr sysOpr = servletUtils.getSysOpr();
        // 当前操作IP
        String currentIp = servletUtils.getCurrentIp();
        // 根据角色ID查询表中对应的菜单数据，以此确定是新增、修改还是删除
        QueryWrapper wrapper = new QueryWrapper().eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectListByQuery(wrapper);
        // 后台存在的角色对应的菜单ID
        Set<String> existingMenuIds = sysRoleMenus.stream()
                .map(SysRoleMenu::getMenuId)
                .collect(Collectors.toSet());

        // 批量删除的菜单集合
        List<String> willDeleteData = existingMenuIds.stream()
                .filter(menuId -> !menuIds.contains(menuId))
                .collect(Collectors.toList());

        // 批量更新的菜单数据
        List<SysRoleMenu> willUpdateData = sysRoleMenus.stream()
                .filter(sysRoleMenu -> menuIds.contains(sysRoleMenu.getMenuId()))
                .peek(sysRoleMenu -> {
                    sysRoleMenu.setOperateIp(currentIp);
                    sysRoleMenu.setUpdateBy(sysOpr.getUserId());
                    sysRoleMenu.setUpdateTime(LocalDateTime.now());
                })
                .collect(Collectors.toList());

        // 批量新增的数据
        List<SysRoleMenu> willAddData = menuIds.stream()
                .filter(menuId -> !existingMenuIds.contains(menuId))
                .map(menuId -> {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setRoleId(roleId);
                    sysRoleMenu.setMenuId(menuId);
                    sysRoleMenu.setOperateIp(currentIp);
                    return sysRoleMenu;
                })
                .collect(Collectors.toList());

        // 删除数据
        if (!willDeleteData.isEmpty()) {
            QueryWrapper deleteWrapper = new QueryWrapper();
            deleteWrapper.eq(SysRoleMenu::getRoleId, roleId);
            deleteWrapper.in(SysRoleMenu::getMenuId, willDeleteData);
            sysRoleMenuMapper.deleteByQuery(deleteWrapper);
        }

        // 更新数据
        if (!willUpdateData.isEmpty()) {
            Db.updateEntitiesBatch(willUpdateData);
        }

        // 新增数据
        if (!willAddData.isEmpty()) {
            sysRoleMenuMapper.insertBatch(willAddData);
        }
        return true;
    }

    /**
     * 给角色分配用户
     *
     * @param roleId  角色ID
     * @param operate 操作类型（add 添加 delete 删除（单个删除和批量删除））
     * @param ids     所有的ID
     * @return 分配结果
     */
    @Override
    public boolean assignRoleUser(String roleId, String operate, List<String> ids) {
        // 当前操作IP
        String currentIp = servletUtils.getCurrentIp();
        // 新增
        if ("add".equals(operate)) {
            List<SysUserRole> userRoles = ids.stream().map(userId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(userId);
                sysUserRole.setOperateIp(currentIp);
                return sysUserRole;
            }).toList();
            if (!userRoles.isEmpty()) {
                sysUserRoleMapper.insertBatch(userRoles);
            }
        } else {
            // 删除(直接传t_sys_user_role表中的id)
            if (!ids.isEmpty()) {
                QueryWrapper deleteWrapper = new QueryWrapper();
                deleteWrapper.in(SysUserRole::getId, ids);
                sysUserRoleMapper.deleteByQuery(deleteWrapper);
            }
        }
        return true;
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
