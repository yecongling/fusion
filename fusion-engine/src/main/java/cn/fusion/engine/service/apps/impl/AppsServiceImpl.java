package cn.fusion.engine.service.apps.impl;

import cn.fusion.engine.camel.core.RouteControlService;
import cn.fusion.engine.camel.core.RouteManager;
import cn.fusion.engine.dto.app.AppQuery;
import cn.fusion.engine.entity.apps.App;
import cn.fusion.engine.entity.apps.ProjectTags;
import cn.fusion.engine.entity.apps.Tags;
import cn.fusion.engine.mapper.apps.AppsMapper;
import cn.fusion.engine.service.apps.IAppsService;
import cn.fusion.framework.core.Response;
import cn.fusion.framework.core.SysOpr;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.camel.ServiceStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName AppsServiceImpl
 * @Description 应用服务实现
 * @Author ycl
 * @Date 2025/1/22 15:58
 * @Version 1.0
 */
@Service
public class AppsServiceImpl implements IAppsService {

    private final AppsMapper appMapper;

    /**
     * 路由管理
     */
    private final RouteManager routeManager;

    /**
     * 路由控制服务
     */
    private final RouteControlService routeControlService;

    @Autowired
    public AppsServiceImpl(AppsMapper appMapper, RouteManager routeManager, RouteControlService routeControlService) {
        this.appMapper = appMapper;
        this.routeManager = routeManager;
        this.routeControlService = routeControlService;
    }

    /**
     * 检索应用（目前参数支持name、type进行检索）
     *
     * @param appQuery 检索条件
     * @param sysOpr       操作人信息
     * @return 应用集合
     */
    @Override
    public List<App> getApps(AppQuery appQuery, SysOpr sysOpr) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        queryWrapper.select(QueryMethods.allColumns(App.class))
                .select(QueryMethods.allColumns(Tags.class))
                .from(App.class).as("p")
                .leftJoin(ProjectTags.class).as("pt").on(ProjectTags::getProjectId, App::getId)
                .leftJoin(Tags.class).as("t").on(Tags::getId, ProjectTags::getTagId);
        queryWrapper.like(App::getName, appQuery.getName(), StringUtils.isNotBlank(appQuery.getName()));
        if (appQuery.getIsMine() != null && appQuery.getIsMine()) {
            queryWrapper.eq(App::getCreateBy, sysOpr.getUserId());
        }
        // 应用类型
        queryWrapper.eq(App::getType, appQuery.getType(), appQuery.getType() != 0);
        // 还要支持标签查询
        if (appQuery.getTagIDs() != null) {
            List<String> tagIds = Arrays.stream(appQuery.getTagIDs().split("\\|")).toList();
            queryWrapper.in(Tags::getId, tagIds);
        }
        return appMapper.selectListByQueryAs(queryWrapper, App.class);
    }

    /**
     * 新增应用
     *
     * @param app 应用
     * @return true | false
     */
    @Override
    public boolean addApp(App app) {
        int insert = appMapper.insert(app);
        return insert > 0;
    }

    /**
     * 更新应用基础信息
     *
     * @param app 应用
     * @return true | false
     */
    @Override
    public boolean updateApp(App app) {
        return appMapper.update(app) > 0;
    }

    /**
     * 检测服务运行状态
     *
     * @param appId 应用id
     */
    @Override
    public ServiceStatus checkServiceStatus(String appId) {
        return ServiceStatus.Stopped;
    }

    /**
     * 删除应用
     *
     * @param appId 应用
     * @return true | false
     */
    @Override
    public Response<String> deleteApp(String appId) {
        // 检查应用是否在运行中
        // 停止服务
        // 删除应用
        return null;
    }

    /**
     * 更新服务状态（启用服务、停止服务）
     *
     * @param app 应用
     * @return 结果
     */
    @Override
    public Object changeServiceStatus(App app) {
        // 检测路由是否存在、服务是否在运行中
        // 启动服务
        // 停止服务
        return null;
    }


    /**
     * 导出应用
     *
     * @param appId 应用id
     * @return 结果
     */
    @Override
    public Object exportApp(String appId) {
        return null;
    }

    /**
     * 导入应用
     *
     * @param app 应用数据（目前暂定为JSON结构）
     * @return 结果
     */
    @Override
    public Object importApp(JSONObject app) {
        return null;
    }
}
