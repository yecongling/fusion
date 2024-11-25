package cn.net.fusion.engine.service.impl;

import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.mapper.EndpointTypeMapper;
import cn.net.fusion.engine.service.IEndpointTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName EndpointTypeServiceImpl
 * @Description 端点类型业务实现
 * @Author ycl
 * @Date 2024/11/23 17:57
 * @Version 1.0
 */
@Service
public class EndpointTypeServiceImpl extends ServiceImpl<EndpointTypeMapper, EndpointType> implements IEndpointTypeService {

    // 数据库操作接口
    private final EndpointTypeMapper endpointTypeMapper;
    @Autowired
    public EndpointTypeServiceImpl(EndpointTypeMapper endpointTypeMapper) {
        this.endpointTypeMapper = endpointTypeMapper;
    }
}
