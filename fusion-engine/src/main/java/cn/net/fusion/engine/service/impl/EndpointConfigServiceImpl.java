package cn.net.fusion.engine.service.impl;

import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.mapper.EndpointTypeMapper;
import cn.net.fusion.engine.service.IEndpointConfigService;
import cn.net.fusion.engine.vo.EndpointConfigTypeVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EndpointConfigServiceImpl
 * @Description 端点类型配置业务实现
 * @Author ycl
 * @Date 2024/11/23 17:57
 * @Version 1.0
 */
@Service
public class EndpointConfigServiceImpl extends ServiceImpl<EndpointTypeMapper, EndpointType> implements IEndpointConfigService {

    // 数据库操作接口
    private final EndpointTypeMapper endpointTypeMapper;
    @Autowired
    public EndpointConfigServiceImpl(EndpointTypeMapper endpointTypeMapper) {
        this.endpointTypeMapper = endpointTypeMapper;
    }

    /**
     * 查询端点配置类型数据
     *
     * @param vo 查询条件
     * @return 端点配置类型数据
     */
    @Override
    public List<EndpointConfigTypeVO> queryEndpointConfigType(EndpointConfigTypeVO vo) {

        return null;
    }
}
