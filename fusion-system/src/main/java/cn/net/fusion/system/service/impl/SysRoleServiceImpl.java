package cn.net.fusion.system.service.impl;

import cn.net.fusion.framework.core.SysOpr;
import cn.net.fusion.framework.utils.ServletUtils;
import cn.net.fusion.system.entity.SysMenu;
import cn.net.fusion.system.entity.SysRole;
import cn.net.fusion.system.entity.SysRoleMenu;
import cn.net.fusion.system.mapper.SysMenuMapper;
import cn.net.fusion.system.mapper.SysRoleMapper;
import cn.net.fusion.system.mapper.SysRoleMenuMapper;
import cn.net.fusion.system.service.ISysRoleService;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    private final ServletUtils servletUtils;

    @Autowired
    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper, SysMenuMapper sysMenuMapper,
                              SysRoleMenuMapper sysRoleMenuMapper, ServletUtils servletUtils) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysMenuMapper = sysMenuMapper;
        this.sysRoleMenuMapper = sysRoleMenuMapper;
        this.servletUtils = servletUtils;
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
        sysRoleMenus.forEach(sysRoleMenu -> {
            menuIds.add(sysRoleMenu.getMenuId());
        });
        jsonObject.put("menuIds", menuIds);
        return jsonObject;
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
        // 根据角色ID查询表中对应的菜单数据，以此确定是新增、修改还是删除
        QueryWrapper wrapper = new QueryWrapper().eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectListByQuery(wrapper);
        // 即将删除的数据
        List<String> willDeleteData = new ArrayList<>();
        // 后台存在的角色对应的菜单ID
        List<String> list = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).toList();
        // 筛选出需要更新的数据（因为roleId和menuId作为双主键，如果和传进来的数据一致，就不用更新，不一致的上一步删除的了）
        List<SysRoleMenu> willUpdateData = new ArrayList<>();
        // 需要新增的数据
        List<SysRoleMenu> willAddData = new ArrayList<>();
        SysOpr sysOpr = servletUtils.getSysOpr();
        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
            // 传进来的数据不包含后台查询到的数据，那不包含的数据则需要删除，否则就更新
            if (!menuIds.contains(sysRoleMenu.getMenuId())) {
                willDeleteData.add(sysRoleMenu.getMenuId());
            } else {
                // 需要更新的数据，设置操作IP
                sysRoleMenu.setOperateIp(servletUtils.getCurrentIp());
                sysRoleMenu.setUpdateBy(sysOpr.getUserId());
                sysRoleMenu.setUpdateTime(new Date());
                willUpdateData.add(sysRoleMenu);
            }
        }
        // 删除数据
        if (!willDeleteData.isEmpty()) {
            QueryWrapper deleteWrapper = new QueryWrapper();
            deleteWrapper.eq(SysRoleMenu::getRoleId, roleId);
            deleteWrapper.in(SysRoleMenu::getMenuId, willDeleteData);
            sysRoleMenuMapper.deleteByQuery(deleteWrapper);
        }

        // 更新数据
        Db.updateEntitiesBatch(willUpdateData);
        // 新增数据（查询到的数据不包含传进来的数据，则就是需要新增的数据）
        for (String s : menuIds) {
            if (!list.contains(s)) {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(s);
                roleMenu.setOperateIp(servletUtils.getCurrentIp());
                willAddData.add(roleMenu);
            }
        }
        sysRoleMenuMapper.insertBatch(willAddData);
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
