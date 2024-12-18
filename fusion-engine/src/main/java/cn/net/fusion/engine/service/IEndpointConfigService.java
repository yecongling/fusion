package cn.net.fusion.engine.service;

import cn.net.fusion.engine.entity.EndpointConfig;
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
     *
     * @param endpointType 端点类型数据
     * @return 返回新增的端点
     */
    EndpointType addEndpointConfigType(EndpointType endpointType);

    /**
     * 修改端点类型数据
     *
     * @param endpointType 端点数据
     * @return 结果
     */
    EndpointType updateEndpointConfigType(EndpointType endpointType);

    /**
     * 根据id获取端点配置数据
     *
     * @param id 端点配置id
     * @return 端点配置数据
     */
    EndpointConfig queryEndpointConfig(String id);

    /**
     * 新增端点配置数据
     *
     * @param endpointConfig 端点配置数据
     * @return 新增结果
     */
    Boolean addEndpointConfig(EndpointConfig endpointConfig);

    /**
     * 批量更新端点的配置数据
     * (端点配置中的属性配置) 有ID的进行更新，没有ID的则进行新增，数据库中多出来的则进行删除
     *
     * @param endpointConfig 端点配置
     * @return 更新结果
     */
    Object updateEndpointConfigBatch(EndpointConfig endpointConfig);

    /**
     * 删除端点配置
     *
     * @param endpointConfigId 端点配置id
     * @return 删除结果
     */
    Object deleteEndpointConfig(String endpointConfigId);
}
