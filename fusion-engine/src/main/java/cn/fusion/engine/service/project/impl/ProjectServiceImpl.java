package cn.fusion.engine.service.project.impl;

import cn.fusion.engine.camel.core.RouteControlService;
import cn.fusion.engine.camel.core.RouteManager;
import cn.fusion.engine.entity.project.Project;
import cn.fusion.engine.entity.project.ProjectTags;
import cn.fusion.engine.entity.project.Tags;
import cn.fusion.engine.mapper.project.ProjectMapper;
import cn.fusion.engine.service.project.IProjectService;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.camel.ServiceStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProjectServiceImpl
 * @Description 项目服务实现
 * @Author ycl
 * @Date 2025/1/22 15:58
 * @Version 1.0
 */
@Service
public class ProjectServiceImpl implements IProjectService {

    private final ProjectMapper projectMapper;

    /**
     * 路由管理
     */
    private final RouteManager routeManager;

    /**
     * 路由控制服务
     */
    private final RouteControlService routeControlService;

    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper, RouteManager routeManager, RouteControlService routeControlService) {
        this.projectMapper = projectMapper;
        this.routeManager = routeManager;
        this.routeControlService = routeControlService;
    }

    /**
     * 检索项目（目前参数支持name、type进行检索）
     *
     * @param project 检索条件
     * @return 项目集合
     */
    @Override
    public List<Project> getProjects(Project project) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        queryWrapper.select(
                        QueryMethods.column(
                                QueryWrapper.create().select(QueryMethods.allColumns(Project.class))
                        ),
                        QueryMethods.column(
                                QueryWrapper.create().select(QueryMethods.allColumns(Project.class))
                        )
                )
                .from(Project.class).as("p")
                .leftJoin(ProjectTags.class).as("pt").on(ProjectTags::getProjectId, Project::getId)
                .leftJoin(Tags.class).as("t").on(Tags::getId, ProjectTags::getTagId);
        // 还要支持标签查询
        queryWrapper.like(Project::getName, project.getName(), StringUtils.isNotBlank(project.getName()));
        queryWrapper.eq(Project::getCreateBy, project.getCreateBy(), project.getCreateBy() != null);
        queryWrapper.eq(Project::getType, project.getType(), project.getType() != 0);
        return projectMapper.selectListByQueryAs(queryWrapper, Project.class);
    }

    /**
     * 新增项目
     *
     * @param project 项目
     * @return true | false
     */
    @Override
    public boolean addProject(Project project) {
        int insert = projectMapper.insert(project);
        return insert > 0;
    }

    /**
     * 更新项目基础信息
     *
     * @param project 项目
     * @return true | false
     */
    @Override
    public boolean updateProject(Project project) {
        return projectMapper.update(project) > 0;
    }

    /**
     * 检测服务运行状态
     *
     * @param projectId 项目id
     */
    @Override
    public ServiceStatus checkServiceStatus(String projectId) {
        return ServiceStatus.Stopped;
    }

    /**
     * 删除项目
     *
     * @param project 项目
     * @return true | false
     */
    @Override
    public boolean deleteProject(Project project) {
        // 检查项目是否在运行中
        // 停止服务
        // 删除项目
        return false;
    }

    /**
     * 更新服务状态（启用服务、停止服务）
     *
     * @param project 项目
     * @return 结果
     */
    @Override
    public Object changeServiceStatus(Project project) {
        // 检测路由是否存在、服务是否在运行中
        // 启动服务
        // 停止服务
        return null;
    }


    /**
     * 导出项目
     *
     * @param projectId 项目id
     * @return 结果
     */
    @Override
    public Object exportProject(String projectId) {
        return null;
    }

    /**
     * 导入项目
     *
     * @param project 项目数据（目前暂定为JSON结构）
     * @return 结果
     */
    @Override
    public Object importProject(JSONObject project) {
        return null;
    }
}
