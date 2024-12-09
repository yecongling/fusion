package cn.net.fusion.engine.service;

import cn.net.fusion.engine.entity.EndpointType;

import java.util.List;

/**
 * @ClassName IEndpointTypeService
 * @Description 端点类型接口
 * @Author ycl
 * @Date 2024/11/23 17:47
 * @Version 1.0
 */
public interface IEndpointConfigService {

    /**
     * 查询端点配置类型数据
     *
     * @param name 查询条件
     * @return 端点配置类型数据
     */
    List<EndpointType> queryEndpointConfigType(String name);

    /**
     * 新增端点类型
     * @param endpointType 端点类型数据
     * @return 返回新增的端点
     */
    EndpointType addEndpointConfig(EndpointType endpointType);
}
