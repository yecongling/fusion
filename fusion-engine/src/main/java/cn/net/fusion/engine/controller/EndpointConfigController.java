package cn.net.fusion.engine.controller;

import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.service.IEndpointConfigService;
import cn.net.fusion.framework.core.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final IEndpointConfigService endpointTypeService;

    @Autowired
    public EndpointConfigController(IEndpointConfigService endpointTypeService) {
        this.endpointTypeService = endpointTypeService;
    }

    /**
     * 查询端点配置分类
     *
     * @param name 类型名或配置端点名
     * @return 端点配置分类集合
     */
    @GetMapping("/queryEndpointConfigType")
    public Response<List<EndpointType>> queryEndpointConfigType(@RequestParam(value = "name", required = false) String name) {
        return Response.success("", endpointTypeService.queryEndpointConfigType(name));
    }

    /**
     * 新增端点类型
     * @param endpointType 端点类型数据
     * @return 返回新增的端点
     */
    @PostMapping("/addEndpointType")
    public Response<EndpointType> addEndpointConfig(@RequestBody @Valid EndpointType endpointType) {
        return Response.success(endpointTypeService.addEndpointConfig(endpointType));
    }
}
