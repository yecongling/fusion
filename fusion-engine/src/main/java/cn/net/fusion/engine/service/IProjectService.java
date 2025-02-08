package cn.net.fusion.engine.service;

import cn.net.fusion.engine.entity.Project;
import com.alibaba.fastjson2.JSONObject;
import org.apache.camel.ServiceStatus;

import java.util.List;

/**
 * @ClassName IProjectService
 * @Description 项目service
 * @Author ycl
 * @Date 2025/1/22 11:02
 * @Version 1.0
 */
public interface IProjectService {

    /**
     * 检索项目（目前参数支持name、type进行检索）
     *
     * @param project 检索条件
     * @return 项目集合
     */
    List<Project> getProjects(Project project);

    /**
     * 新增项目
     *
     * @param project 项目
     * @return true | false
     */
    boolean addProject(Project project);

    /**
     * 更新项目基础信息
     *
     * @param project 项目
     * @return true | false
     */
    boolean updateProject(Project project);

    /**
     * 检测服务运行状态
     * @param projectId 项目id
     */
    ServiceStatus checkServiceStatus(String projectId);

    /**
     * 删除项目
     *
     * @param project 项目
     * @return true | false
     */
    boolean deleteProject(Project project);

    /**
     * 更新服务状态（启用服务、停止服务）
     *
     * @param project 项目
     * @return 结果
     */
    Object changeServiceStatus(Project project);

    /**
     * 导出项目
     *
     * @param projectId 项目id
     * @return 结果
     */
    Object exportProject(String projectId);

    /**
     * 导入项目
     *
     * @param project 项目数据（目前暂定为JSON结构）
     * @return 结果
     */
    Object importProject(JSONObject project);
}
