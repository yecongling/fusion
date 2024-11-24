package cn.net.fusion.integration.controller;

import cn.net.fusion.integration.service.IEndpointTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EndpointTypeController
 * @Description 端点类型维护接口
 * @Author ycl
 * @Date 2024/11/23 17:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/engine/endpoint-type")
public class EndpointTypeController {

    // 端点类型操作业务实现
    private final IEndpointTypeService endpointTypeService;
    @Autowired
    public EndpointTypeController(IEndpointTypeService endpointTypeService) {
        this.endpointTypeService = endpointTypeService;
    }
}
