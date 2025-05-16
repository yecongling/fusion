package cn.fusion.engine.controller.apps;

import cn.fusion.engine.dto.app.AppQuery;
import cn.fusion.engine.entity.apps.App;
import cn.fusion.engine.service.apps.IAppsService;
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
 * @ClassName AppsController
 * @Description 应用相关入口
 * @Author ycl
 * @Date 2025/1/20 10:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/engine/apps")
public class AppsController {

    private final IAppsService appsService;

    @Autowired
    public AppsController(IAppsService appsService) {
        this.appsService = appsService;
    }

    /**
     * 获取所有的应用
     *
     * @param appQuery 查询条件
     * @param sysOpr       操作人信息
     * @return 应用集合
     */
    @GetMapping("/getApps")
    public List<App> getApps(AppQuery appQuery, @CurrentUser SysOpr sysOpr) {
        return appsService.getApps(appQuery, sysOpr);
    }

    /**
     * 新增应用
     *
     * @param app 应用数据
     * @return 结果
     */
    @PostMapping("/addApp")
    public Response<Boolean> addApp(@RequestBody @Valid App app, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        return Response.success(appsService.addApp(app));
    }

    /**
     * 修改应用
     *
     * @param app 应用数据
     * @return 结果
     */
    @PostMapping("/updateApp")
    public Response<Boolean> updateApp(@RequestBody @Valid App app, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        return Response.success(appsService.updateApp(app));
    }
}
