package cn.net.fusion.engine.mapper;

import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.vo.EndpointConfigTypeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName EndpointTypeMapper
 * @Description 端点类型mapper
 * @Author ycl
 * @Date 2024/11/23 17:46
 * @Version 1.0
 */
public interface EndpointTypeMapper extends BaseMapper<EndpointType> {

    @Select("select *")
    List<EndpointConfigTypeVO> selectConfigTypeList(EndpointConfigTypeVO vo);
}
