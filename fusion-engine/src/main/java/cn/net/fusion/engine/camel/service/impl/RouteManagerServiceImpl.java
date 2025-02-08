package cn.net.fusion.engine.camel.service.impl;

import cn.net.fusion.engine.camel.service.IRouteManagerService;
import cn.net.fusion.engine.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RouteManagerServiceImpl
 * @Description 路由管理服务实现类
 * @Author 49947
 * @Date 2025/2/8 17:25
 * @Version 1.0
 */
@Service
public class RouteManagerServiceImpl implements IRouteManagerService {

    /**
     * 构建项目DTO列表
     *
     * @param projectId 项目ID
     * @return 项目DTO列表
     */
    @Override
    public List<ProjectDTO> buildProjectDTOList(String projectId) {
        return List.of();
    }

}
