package cn.net.fusion.engine.camel.core;

import org.apache.camel.ServiceStatus;
import org.apache.camel.spi.RouteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName RouteControlService
 * @Description 路由控制服务
 * @Author 49947
 * @Date 2025/2/8 17:48
 * @Version 1.0
 */
@Service
public class RouteControlService {

    private final RouteManager routeManager;
    @Autowired
    public RouteControlService(RouteManager routeManager) {
        this.routeManager = routeManager;
    }

    /**
     * 启动路由
     * @param routeId 路由ID
     */
    public void startRoute(String routeId) {

    }

    /**
     * 停止路由
     * @param routeId 路由ID
     */
    public void stopRoute(String routeId) {

    }

    /**
     * 重启路由
     * @param routeId 路由ID
     */
    public void restartRoute(String routeId) {

    }

    /**
     * 获取路由的运行状态
     * @param routeId 路由ID
     */
    public ServiceStatus checkRouteStatus(String routeId) {
        RouteController routeController = routeManager.getRouteController();
        if (routeController != null) {
            return routeController.getRouteStatus(routeId);
        }
        return ServiceStatus.Stopped;
    }
}
