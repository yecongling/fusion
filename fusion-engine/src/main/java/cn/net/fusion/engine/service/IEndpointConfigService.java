package cn.net.fusion.engine.service;

import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.vo.EndpointConfigTypeVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName IEndpointTypeService
 * @Description 端点类型接口
 * @Author ycl
 * @Date 2024/11/23 17:47
 * @Version 1.0
 */
public interface IEndpointConfigService extends IService<EndpointType> {

    /**
     * 查询端点配置类型数据
     *
     * @param name 查询条件
     * @return 端点配置类型数据
     */
    List<EndpointConfigTypeVO> queryEndpointConfigType(String name);
}
