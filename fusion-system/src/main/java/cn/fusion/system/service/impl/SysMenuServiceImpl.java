package cn.fusion.system.service.impl;

import cn.fusion.framework.constant.CommonConstant;
import cn.fusion.framework.constant.SymbolConstant;
import cn.fusion.framework.core.Response;
import cn.fusion.framework.core.SysOpr;
import cn.fusion.framework.utils.ServletUtils;
import cn.fusion.system.entity.SysMenu;
import cn.fusion.system.entity.SysRoleMenu;
import cn.fusion.system.mapper.SysMenuMapper;
import cn.fusion.system.service.ISysMenuService;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.util.UpdateEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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


    // 获取操作员
    private final ServletUtils servletUtils;

    @Autowired
    public SysMenuServiceImpl(SysMenuMapper sysMenuMapper, ServletUtils servletUtils) {
        this.sysMenuMapper = sysMenuMapper;
        this.servletUtils = servletUtils;
    }

    /**
     * 获取所有菜单
     *
     * @param menu 菜单查询条件
     * @return 符合条件的菜单
     */
    @Override
    public List<SysMenu> getAllMenus(SysMenu menu) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name", menu.getName());

        queryWrapper.orderBy("sort_no", true);
        List<SysMenu> sysMenus = sysMenuMapper.selectListByQuery(queryWrapper);
        // 构建上下级关系的树结构数据
        return this.buildMenus(sysMenus);
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
        QueryWrapper queryWrapper = new QueryWrapper();
        // 查询菜单表的字段
        queryWrapper.select(
                        QueryMethods.allColumns(SysMenu.class)
                )
                // 从角色菜单表
                .from(SysRoleMenu.class).as("role_menu")
                // 关联菜单表
                .leftJoin(SysMenu.class).as("menu")
                .on(SysRoleMenu::getMenuId, SysMenu::getId)
                // 查询条件
                .eq(SysRoleMenu::getRoleId, roleId).and(SysMenu::getDelFlag).eq(0)
                // 根据序号排序
                .orderBy(SysMenu::getSortNo, true);

        List<SysMenu> permissions = sysMenuMapper.selectListByQuery(queryWrapper);
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
    public JSONArray getDirectory() {
        QueryWrapper queryWrapper = new QueryWrapper();
        // 查询的字段
        queryWrapper.select(
                QueryMethods.column(SysMenu::getId),
                QueryMethods.column(SysMenu::getParentId),
                QueryMethods.column(SysMenu::getName)
        );
        // 排序
        queryWrapper.orderBy(SysMenu::getSortNo, true);

        List<SysMenu> directory = sysMenuMapper.selectListByQuery(queryWrapper);
        // 构建成树结构
        JSONArray array = new JSONArray();
        this.buildDirectory(array, directory, null);
        return array;
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单
     * @return 结果
     */
    @Override
    public Response<Integer> addMenu(SysMenu menu) {
        // 受影响的行数
        int num = sysMenuMapper.insert(menu);
        return num > 0 ? Response.success() : Response.fail();
    }

    /**
     * 更新菜单
     *
     * @param menu 菜单对象
     * @return 结果
     */
    @Override
    public Response<Integer> modifyMenu(SysMenu menu) {
        int update = sysMenuMapper.update(menu);
        return update > 0 ? Response.success() : Response.fail();
    }

    /**
     * 删除菜单 假删除，将del_flag置为1
     *
     * @param id 菜单ID
     * @return -
     */
    @Override
    public Response<Integer> deleteMenu(String id) {
        // 这里需要判断菜单是否在使用中（如果菜单在使用中，则不允许删除）
        SysMenu sysMenu = UpdateEntity.of(SysMenu.class, id);
        sysMenu.setDelFlag(1);

        int update = sysMenuMapper.update(sysMenu);
        return update > 0 ? Response.success() : Response.fail();
    }

    /**
     * 批量删除
     *
     * @param ids 菜单id
     * @return true 删除成功 false 删除失败
     */
    @Transactional
    @Override
    public Boolean deleteBatch(List<String> ids) {
        SysOpr sysOpr = servletUtils.getSysOpr();
        // 利用id转换为menu对象
        List<SysMenu> menus = new ArrayList<>();
        ids.forEach(id -> {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(id);
            sysMenu.setDelFlag(1);
            sysMenu.setUpdateBy(sysOpr.getUserId());
            sysMenu.setUpdateTime(LocalDateTime.now());
            menus.add(sysMenu);
        });
        int i = Db.updateEntitiesBatch(menus);
        // 抛出异常，事务就会回滚
        if (i == 0) throw new RuntimeException("批量删除菜单失败！");
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

    /**
     * 将查询到的菜单目录构建成树结构
     *
     * @param array      数组
     * @param menus      查询到的菜单
     * @param parentJSON 父级
     */
    private void buildDirectory(JSONArray array, List<SysMenu> menus, JSONObject parentJSON) {
        if (menus == null || menus.isEmpty()) {
            return;
        }
        for (SysMenu menu : menus) {
            JSONObject json = getMenuDirectory(menu);
            String parentId = menu.getParentId();
            if (parentJSON == null && StringUtils.isEmpty(parentId)) {
                array.add(json);
            } else if (parentJSON != null && StringUtils.isNotEmpty(parentId) && parentId.equals(parentJSON.get("id"))) {
                if (parentJSON.containsKey("children")) {
                    parentJSON.getJSONArray("children").add(json);
                } else {
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.add(json);
                    parentJSON.put("children", jsonArray);
                }
                if (!menu.getLeaf()) {
                    buildDirectory(array, menus, json);
                }
            }

        }
    }

    /**
     * 将menu中的ID和name抽出来作为前端树结构的数据
     *
     * @param menu 菜单对象
     * @return json
     */
    private JSONObject getMenuDirectory(SysMenu menu) {
        JSONObject json = new JSONObject();
        json.put("value", menu.getId());
        json.put("title", menu.getName());
        return json;
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
                if (!menu.getLeaf()) {
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
                    if (!menu.getLeaf()) {
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
        JSONObject json = new JSONObject();
        json.put("id", permission.getId());
        // 类型 0 一级菜单  1 子菜单  2 子路由 3 按钮
        if (permission.getMenuType().equals(CommonConstant.MENU_TYPE_3)) {
            // 如果是按钮，则构建其他形式的结构

            return null;
        }
        // 表示生成路由
        json.put("route", permission.getRoute() ? "1" : "0");
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
