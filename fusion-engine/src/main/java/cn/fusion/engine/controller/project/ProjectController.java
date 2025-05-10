package cn.fusion.engine.controller.project;

import cn.fusion.engine.dto.project.ProjectQuery;
import cn.fusion.engine.entity.project.Project;
import cn.fusion.engine.service.project.IProjectService;
import cn.fusion.framework.annotation.CurrentUser;
import cn.fusion.framework.core.Response;
import cn.fusion.framework.core.SysOpr;
import cn.fusion.framework.enums.HttpCodeEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
     * @param projectQuery 查询条件
     * @param sysOpr       操作人信息
     * @return 项目集合
     */
    @GetMapping("/getProjectList")
    public List<Project> getProjectList(ProjectQuery projectQuery, @CurrentUser SysOpr sysOpr) {
        return projectService.getProjects(projectQuery, sysOpr);
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
     * 修改项目
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
