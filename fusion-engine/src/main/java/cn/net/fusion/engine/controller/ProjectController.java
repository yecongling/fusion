package cn.net.fusion.engine.controller;

import cn.net.fusion.engine.entity.Project;
import cn.net.fusion.engine.service.IProjectService;
import cn.net.fusion.framework.core.Response;
import cn.net.fusion.framework.enums.HttpCodeEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ProjectController
 * @Description 项目相关入口
 * @Author ycl
 * @Date 2025/1/20 10:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/engine/project")
public class ProjectController {

    private final IProjectService projectService;

    @Autowired
    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 获取所有的项目
     *
     * @param project 查询条件
     * @return 项目集合
     */
    @PostMapping("/getProjectList")
    public List<Project> getProjectList(@RequestBody Project project) {
        return projectService.getProjects(project);
    }

    /**
     * 新增项目
     *
     * @param project 项目数据
     * @return 结果
     */
    @PostMapping("/addProject")
    public Response<Boolean> addProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        return Response.success(projectService.addProject(project));
    }

    /**
     * 新增项目
     *
     * @param project 项目数据
     * @return 结果
     */
    @PostMapping("/modifyProject")
    public Response<Boolean> modifyProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        return Response.success(projectService.updateProject(project));
    }
}
