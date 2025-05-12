package cn.fusion.engine.camel.service;

import cn.fusion.engine.dto.project.ProjectDTO;

import java.util.List;

/**
 * @ClassName IRouteManagerService
 * @Description 路由管理服务接口
 * @Author 49947
 * @Date 2025/2/8 17:23
 * @Version 1.0
 */
public interface IRouteManagerService {

    /**
     * 构建项目DTO列表
     * @param projectId 项目ID
     * @return 项目DTO列表
     */
    List<ProjectDTO> buildProjectDTOList(String projectId);
}
