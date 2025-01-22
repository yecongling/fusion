package cn.net.fusion.engine.controller;

import cn.net.fusion.engine.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
