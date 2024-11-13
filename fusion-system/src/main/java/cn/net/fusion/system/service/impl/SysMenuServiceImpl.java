package cn.net.fusion.system.service.impl;

import cn.net.fusion.framework.constant.CommonConstant;
import cn.net.fusion.framework.constant.SymbolConstant;
import cn.net.fusion.framework.core.Response;
import cn.net.fusion.system.entity.SysMenu;
import cn.net.fusion.system.mapper.SysMenuMapper;
import cn.net.fusion.system.service.ISysMenuService;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
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
     * 根据角色获取菜单(目前暂时配置为获取全部的菜单，后面做了用户、角色过后再进行分配)
     *
     * @param roleId 角色id
     * @return 菜单信息
     */
    @Override
    public JSONArray getMenusByRoleId(String roleId) {
        // 根据userId获取对应的菜单
        List<SysMenu> permissions = sysMenuMapper.queryByUser(roleId);
        // 将平级的菜单构建成上下结构的格式
        JSONArray array = new JSONArray();
        this.getPermissionJsonArray(array, permissions, null);
        return array;
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

    /**
     * 获取菜单json数组
     *
     * @param array       菜单列表
     * @param permissions 菜单数据
     * @param parentJSON  父级
     */
    private void getPermissionJsonArray(JSONArray array, List<SysMenu> permissions, JSONObject parentJSON) {
        for (SysMenu menu : permissions) {
            if (menu.getMenuType() == null) {
                continue;
            }
            String parentId = menu.getParentId();
            JSONObject json = getPermissionJsonObject(menu);
            if (json == null) {
                continue;
            }
            if (parentJSON == null && StringUtils.isEmpty(parentId)) {
                array.add(json);
                if (!menu.isLeaf()) {
                    getPermissionJsonArray(array, permissions, json);
                }
            } else if (parentJSON != null && StringUtils.isNotEmpty(parentId) && parentId.equals(parentJSON.get("id"))) {
                // 类型( 0：一级菜单 1：子菜单 2：子路由  3：按钮 )
                if (menu.getMenuType().equals(CommonConstant.MENU_TYPE_3)) {
                    JSONObject meta = parentJSON.getJSONObject("meta");
                    if (meta.containsKey("permissionList")) {
                        meta.getJSONArray("permissionList").add(json);
                    } else {
                        JSONArray permissionList = new JSONArray();
                        permissionList.add(json);
                        meta.put("permissionList", permissionList);
                    }
                } else if (menu.getMenuType().equals(CommonConstant.MENU_TYPE_1) || menu.getMenuType().equals(CommonConstant.MENU_TYPE_0)) {
                    if (parentJSON.containsKey("children")) {
                        parentJSON.getJSONArray("children").add(json);
                    } else {
                        JSONArray children = new JSONArray();
                        children.add(json);
                        parentJSON.put("children", children);
                    }
                    if (!menu.isLeaf()) {
                        getPermissionJsonArray(array, permissions, json);
                    }
                } else if (menu.getMenuType().equals(CommonConstant.MENU_TYPE_2)) {
                    // 处理子路由，即不能再左边菜单列表中进行路由的菜单，但是页面内部又要能路由过去的
                    if (parentJSON.containsKey("childrenRoute")) {
                        parentJSON.getJSONArray("childrenRoute").add(json);
                    } else {
                        JSONArray jsonArray = new JSONArray();
                        jsonArray.add(json);
                        parentJSON.put("childrenRoute", jsonArray);
                    }
                }
            }
        }
    }

    /**
     * 根据菜单配置生成路由json
     *
     * @param permission 菜单权限
     * @return json
     */
    private JSONObject getPermissionJsonObject(SysMenu permission) {
        // 类型 0 一级菜单  1 子菜单  2 子路由 3 按钮
        if (permission.getMenuType().equals(CommonConstant.MENU_TYPE_3)) {
            return null;
        }
        JSONObject json = new JSONObject();
        json.put("id", permission.getId());
        // 表示生成路由
        json.put("route", permission.isRoute() ? "1" : "0");
        json.put("path", permission.getUrl());
        if (StringUtils.isNotEmpty(permission.getComponentName())) {
            json.put("name", permission.getComponentName());
        } else {
            json.put("name", urlToRouteName(permission.getUrl()));
        }
        // 元数据
        JSONObject meta = new JSONObject();
        // 是否隐藏路由，默认都显示
        if (permission.isHidden()) {
            json.put("hidden", true);
        }
        json.put("component", permission.getComponent());
        meta.put("keepAlive", permission.isKeepAlive());
        // 外链菜单打开方式
        meta.put("internalOrExternal", permission.isInternalOrExternal());
        meta.put("title", permission.getName());
        meta.put("componentName", permission.getComponentName());
        if (StringUtils.isEmpty(permission.getParentId())) {
            // 一级菜单跳转地址
            json.put("redirect", permission.getRedirect());
        }
        if (StringUtils.isNotEmpty(permission.getIcon())) {
            meta.put("icon", permission.getIcon());
        }
        if (isWwwHttpUrl(permission.getUrl())) {
            meta.put("url", permission.getUrl());
        }
        json.put("meta", meta);
        return json;
    }

    /**
     * 判断是否外网URL 例如： <a href="http://localhost:8080/jeecg-boot/swagger-ui.html#/">...</a> 支持特殊格式： {{
     * window._CONFIG['domianURL'] }}/druid/ {{ JS代码片段 }}，前台解析会自动执行JS代码片段
     *
     * @return true / false
     */
    private boolean isWwwHttpUrl(String url) {
        return url != null && (url.startsWith(CommonConstant.HTTP_PROTOCOL) || url.startsWith(CommonConstant.HTTPS_PROTOCOL) || url.startsWith(SymbolConstant.DOUBLE_LEFT_CURLY_BRACKET));
    }

    /**
     * 通过URL生成路由name（去掉URL前缀斜杠，替换内容中的斜杠‘/’为-） 举例： URL = /system/role RouteName =
     * system-role
     *
     * @return /
     */
    private String urlToRouteName(String url) {
        if (StringUtils.isNotEmpty(url)) {
            if (url.startsWith(SymbolConstant.SINGLE_SLASH)) {
                url = url.substring(1);
            }
            url = url.replace("/", "-");

            // 特殊标记
            url = url.replace(":", "@");
            return url;
        } else {
            return null;
        }
    }
}
