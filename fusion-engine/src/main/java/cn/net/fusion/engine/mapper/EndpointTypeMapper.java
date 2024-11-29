package cn.net.fusion.engine.mapper;

import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.vo.EndpointConfigTypeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName EndpointTypeMapper
 * @Description 端点类型mapper
 * @Author ycl
 * @Date 2024/11/23 17:46
 * @Version 1.0
 */
public interface EndpointTypeMapper extends BaseMapper<EndpointType> {

    /**
     * 查询端点配置分类
     *
     * @param name 类型名或配置端点名
     * @return 端点配置分类集合
     */
    List<EndpointConfigTypeVO> selectConfigTypeList(String name);
}
