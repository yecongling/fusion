package cn.net.fusion.engine.camel.route;

import cn.net.fusion.engine.dto.ProjectDTO;
import org.apache.camel.builder.RouteBuilder;

/**
 * @ClassName BaseRoute
 * @Description 基础路由
 * @Author 49947
 * @Date 2025/2/8 16:48
 * @Version 1.0
 */
public class BaseRoute extends RouteBuilder {

    /**
     * 结构化路由数据
     */
    private final ProjectDTO projectDto;
    public BaseRoute(ProjectDTO projectDto) {
        this.projectDto = projectDto;
    }

    /**
     * 路由的运转流程
     * @throws Exception 异常
     */
    @Override
    public void configure() throws Exception {

    }
}
