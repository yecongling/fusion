package cn.net.fusion.engine.service.impl;

import cn.net.fusion.engine.entity.Project;
import cn.net.fusion.engine.mapper.ProjectMapper;
import cn.net.fusion.engine.service.IProjectService;
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
    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    /**
     * 检索项目（目前参数支持name、type进行检索）
     *
     * @param project 检索条件
     * @return 项目集合
     */
    @Override
    public List<Project> getProjects(Project project) {

        return List.of();
    }

    /**
     * 新增项目
     *
     * @param project 项目
     * @return true | false
     */
    @Override
    public boolean addProject(Project project) {
        return false;
    }

    /**
     * 更新项目
     *
     * @param project 项目
     * @return true | false
     */
    @Override
    public boolean updateProject(Project project) {
        return false;
    }

    /**
     * 删除项目
     *
     * @param project 项目
     * @return true | false
     */
    @Override
    public boolean deleteProject(Project project) {
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
        return null;
    }
}
