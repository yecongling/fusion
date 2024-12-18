package cn.net.fusion.engine.controller;

import cn.net.fusion.engine.entity.EndpointConfig;
import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.service.IEndpointConfigService;
import cn.net.fusion.framework.core.Response;
import cn.net.fusion.framework.enums.HttpCodeEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName EndpointTypeController
 * @Description 端点类型维护接口
 * @Author ycl
 * @Date 2024/11/23 17:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/engine/endpointConfig")
public class EndpointConfigController {

    // 端点类型操作业务实现
    private final IEndpointConfigService endpointConfigService;

    @Autowired
    public EndpointConfigController(IEndpointConfigService endpointConfigService) {
        this.endpointConfigService = endpointConfigService;
    }

    /**
     * 查询端点配置分类
     *
     * @param name 类型名或配置端点名
     * @return 端点配置分类集合
     */
    @GetMapping("/queryEndpointConfigType")
    public Response<List<EndpointType>> queryEndpointConfigType(@RequestParam(value = "name", required = false) String name) {
        return Response.success("", endpointConfigService.queryEndpointConfigType(name));
    }

    /**
     * 新增端点类型
     *
     * @param endpointType 端点类型数据
     * @return 返回新增的端点
     */
    @PostMapping("/addEndpointType")
    public Response<EndpointType> addEndpointConfig(@RequestBody @Valid EndpointType endpointType) {
        return Response.success("新增端点类型成功！", endpointConfigService.addEndpointConfigType(endpointType));
    }

    /**
     * 修改端点类型
     *
     * @param endpointType 端点类型数据
     * @return 修改结果
     */
    @PostMapping("/updateEndpointType")
    public Response<EndpointType> updateEndpointType(@RequestBody @Valid EndpointType endpointType) {
        return Response.success("修改端点类型成功！", endpointConfigService.updateEndpointConfigType(endpointType));
    }

    /*=========================端点配置数据和属性配置数据相关===========================*/

    /**
     * 查询端点配置数据
     *
     * @param id 端点配置id
     * @return 端点配置数据
     */
    @GetMapping("/queryEndpointConfig")
    public Response<EndpointConfig> queryEndpointConfig(@RequestParam(value = "id") String id) {
        return Response.success("", endpointConfigService.queryEndpointConfig(id));
    }

    /**
     * 新增端点配置数据
     *
     * @param endpointConfig 端点配置数据
     * @param bindingResult  参数验证
     * @return 新增结果
     */
    @PostMapping("/addEndpointConfig")
    public Response<Object> addEndpointConfig(@RequestBody @Valid EndpointConfig endpointConfig, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return Response.success("新增端点配置数据成功", endpointConfigService.addEndpointConfig(endpointConfig));
    }

    /**
     * 更新端点配置数据
     *
     * @param endpointConfig 端点配置数据
     * @return 更新结果
     */
    @PostMapping("/updateEndpointConfig")
    public Response<Object> updateEndpointConfig(@RequestBody @Valid EndpointConfig endpointConfig, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(HttpCodeEnum.RC400.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return Response.success("更新端点配置数据成功", endpointConfigService.updateEndpointConfigBatch(endpointConfig));
    }

    /**
     * 删除端点配置数据
     *
     * @param id 端点配置id
     * @return 删除结果
     */
    @DeleteMapping("/deleteEndpointConfig")
    public Response<Object> deleteEndpointConfig(@RequestParam(value = "id") String id) {
        return Response.success("端点配置删除成功！", endpointConfigService.deleteEndpointConfig(id));
    }
}
