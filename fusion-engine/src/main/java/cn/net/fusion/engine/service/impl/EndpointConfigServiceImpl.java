package cn.net.fusion.engine.service.impl;

import cn.net.fusion.engine.entity.EndpointConfig;
import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.mapper.EndpointConfigMapper;
import cn.net.fusion.engine.mapper.EndpointTypeMapper;
import cn.net.fusion.engine.service.IEndpointConfigService;
import cn.net.fusion.framework.exception.BusinessException;
import cn.net.fusion.framework.utils.SnowFlakeGenerator;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EndpointConfigServiceImpl
 * @Description 端点类型配置业务实现
 * @Author ycl
 * @Date 2024/11/23 17:57
 * @Version 1.0
 */
@Service
@SuppressWarnings({"varargs", "unchecked", "unused"})
public class EndpointConfigServiceImpl implements IEndpointConfigService {

    // 数据库操作接口
    private final EndpointTypeMapper endpointTypeMapper;
    private final EndpointConfigMapper endpointConfigMapper;
    // 雪花ID生成
    private final SnowFlakeGenerator snowFlakeGenerator;


    @Autowired
    public EndpointConfigServiceImpl(EndpointTypeMapper endpointTypeMapper,
                                     EndpointConfigMapper endpointConfigMapper,
                                     SnowFlakeGenerator snowFlakeGenerator) {
        this.endpointTypeMapper = endpointTypeMapper;
        this.endpointConfigMapper = endpointConfigMapper;
        this.snowFlakeGenerator = snowFlakeGenerator;
    }

    /**
     * 查询端点配置类型数据
     *
     * @param name 查询条件 类型名或配置名
     * @return 端点配置类型数据
     */
    @Override
    public List<EndpointType> queryEndpointConfigType(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        // 传输的name不为空的时候查询
        queryWrapper.like(EndpointType::getTypeName, name);
        // 选择需要的字段
        queryWrapper.select(EndpointType::getTypeName, EndpointType::getId, EndpointType::getParentId);
        List<EndpointType> endpointTypes = endpointTypeMapper.selectListByQuery(queryWrapper);
        Map<String, EndpointType> mapping = new HashMap<>();
        for (EndpointType endpointType : endpointTypes) {
            mapping.put(endpointType.getId(), endpointType);
        }
        List<EndpointType> result = new ArrayList<>();
        // 查询配置数据，然后加到mapping中的对应的节点的children下
        queryWrapper.clear();
        queryWrapper.like(EndpointConfig::getConfigName, name);
        // 选择需要的字段
        queryWrapper.select(EndpointConfig::getId, EndpointConfig::getConfigName, EndpointConfig::getTypeId, EndpointConfig::getIcon);
        List<EndpointConfig> endpointConfigs = endpointConfigMapper.selectListByQuery(queryWrapper);
        // 将配置数据合并到映射中去
        for (EndpointConfig endpointConfig : endpointConfigs) {
            EndpointType endpointType = mapping.get(endpointConfig.getTypeId());
            if (endpointType != null) {
                endpointType.getEndpointConfigs().add(endpointConfig);
            }
        }
        // 构建类型树结构
        this.buildTypeTree(result, endpointTypes, mapping);
        return result;
    }


    /**
     * 新增端点类型
     *
     * @param endpointType 端点类型数据
     * @return 返回新增的端点
     */
    @Override
    public EndpointType addEndpointConfig(EndpointType endpointType) {
        // 新增的时候需要生成id
//        endpointType.setId(snowFlakeGenerator.generateUniqueId());
        int insert = endpointTypeMapper.insert(endpointType);
        if (insert > 0) {
            return endpointType;
        }
        throw new BusinessException("新增端点类型失败！受影响的行数为0！");
    }


    /**
     * 修改端点类型数据
     *
     * @param endpointType 端点数据
     * @return 结果
     */
    @Override
    public EndpointType updateEndpointConfig(EndpointType endpointType) {
        int update = endpointTypeMapper.update(endpointType);
        if (update > 0) {
            return endpointType;
        }
        throw new BusinessException("更新端点类型失败！受影响的行数为0！");
    }

    /**
     * 将类型数据构建成树结构
     *
     * @param result   结果数据
     * @param typeList 类型数据
     * @param mapping  映射 将id设为key做后续数据获取需要的映射
     */
    private void buildTypeTree(List<EndpointType> result, List<EndpointType> typeList, Map<String, EndpointType> mapping) {
        // 将子节点添加到父节点的children中去
        for (EndpointType endpointType : typeList) {
            if (endpointType.getParentId() != null) {
                EndpointType parent = mapping.get(endpointType.getParentId());
                if (parent != null) {
                    parent.getChildren().add(endpointType);
                }
            }
        }
        // 找到根节点（parentId为null的节点）
        for (EndpointType endpointType : typeList) {
            if (endpointType.getParentId() == null) {
                result.add(endpointType);
            }
        }
    }
}
