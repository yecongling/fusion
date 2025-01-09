package cn.net.fusion.system.service.impl;

import cn.net.fusion.system.entity.SysMenu;
import cn.net.fusion.system.entity.SysRole;
import cn.net.fusion.system.entity.SysRoleMenu;
import cn.net.fusion.system.mapper.SysMenuMapper;
import cn.net.fusion.system.mapper.SysRoleMapper;
import cn.net.fusion.system.mapper.SysRoleMenuMapper;
import cn.net.fusion.system.service.ISysRoleService;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper, SysMenuMapper sysMenuMapper, SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysMenuMapper = sysMenuMapper;
        this.sysRoleMenuMapper = sysRoleMenuMapper;
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
        queryWrapper.select(SysRole::getId, SysRole::getRoleName, SysRole::getRoleCode, SysRole::getRoleName, SysRole::getStatus, SysRole::getRoleType, SysRole::getRemark);
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
        sysRoleMenus.forEach(sysRoleMenu -> {menuIds.add(sysRoleMenu.getMenuId());});
        jsonObject.put("menuIds", menuIds);
        return jsonObject;
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
