package cn.fusion.engine.camel.core;

import cn.fusion.engine.camel.route.BaseRoute;
import cn.fusion.engine.camel.service.IRouteManagerService;
import cn.fusion.engine.dto.project.ProjectDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.spi.RouteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RouteManager
 * @Description 全局的路由管理器，用于路由的添加、删除、暂停等操作
 * @Author 49947
 * @Date 2025/2/8 16:57
 * @Version 1.0
 */
@Service
public class RouteManager {

    /**
     * 路由上下文
     */
    private final CamelContext camelContext;

    /**
     * 路由管理服务
     */
    private final IRouteManagerService routeManagerService;

    @Autowired
    public RouteManager(CamelContext camelContext, IRouteManagerService routeManagerService) {
        this.camelContext = camelContext;
        this.routeManagerService = routeManagerService;
    }

    /**
     * 添加路由
     *
     * @param projectId 项目ID
     */
    public void addRoute(String projectId) {
        // 通过项目ID获取路由配置信息
        List<ProjectDTO> projectDTOS = routeManagerService.buildProjectDTOList(projectId);
        try {
            // 构建路由
            for (ProjectDTO projectDTO : projectDTOS) {
                BaseRoute route = new BaseRoute(projectDTO);
                // 添加路由
                camelContext.addRoutes(route);
            }
        } catch (Exception e) {
            // 记录日志
        }
    }

    /**
     * 获取路由，用于判定路由的状态
     *
     * @param routeId 路由ID
     */
    public Route getRoute(String routeId) {
        return camelContext.getRoute(routeId);
    }

    /**
     * 获取路由的控制器服务
     */
    public RouteController getRouteController() {
        return camelContext.getRouteController();
    }

}
