package cn.net.fusion.engine.service.impl;

import cn.net.fusion.engine.entity.EndpointConfig;
import cn.net.fusion.engine.entity.EndpointProperties;
import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.mapper.EndpointConfigMapper;
import cn.net.fusion.engine.mapper.EndpointPropertiesMapper;
import cn.net.fusion.engine.mapper.EndpointTypeMapper;
import cn.net.fusion.engine.service.IEndpointConfigService;
import cn.net.fusion.framework.exception.BusinessException;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final EndpointPropertiesMapper endpointPropertiesMapper;

    @Autowired
    public EndpointConfigServiceImpl(EndpointTypeMapper endpointTypeMapper,
                                     EndpointConfigMapper endpointConfigMapper,
                                     EndpointPropertiesMapper endpointPropertiesMapper) {
        this.endpointTypeMapper = endpointTypeMapper;
        this.endpointConfigMapper = endpointConfigMapper;
        this.endpointPropertiesMapper = endpointPropertiesMapper;
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
    public EndpointType addEndpointConfigType(EndpointType endpointType) {
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
    public EndpointType updateEndpointConfigType(EndpointType endpointType) {
        int update = endpointTypeMapper.update(endpointType);
        if (update > 0) {
            return endpointType;
        }
        throw new BusinessException("更新端点类型失败！受影响的行数为0！");
    }

    /**
     * 根据id获取端点配置数据
     *
     * @param id 端点配置id
     * @return 端点配置数据
     */
    @Override
    public EndpointConfig queryEndpointConfig(String id) {
        // 查询端点配置
        EndpointConfig endpointConfig = endpointConfigMapper.selectOneById(id);
        // 查询端点对应的属性配置
        List<EndpointProperties> endpointProperties = endpointPropertiesMapper.selectListByQuery(new QueryWrapper().eq(EndpointConfig::getConfig, id));
        endpointConfig.setEndpointProperties(endpointProperties);
        return endpointConfig;
    }

    /**
     * 新增端点配置数据
     *
     * @param endpointConfig 端点配置数据
     * @return 新增结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addEndpointConfig(EndpointConfig endpointConfig) {
        // 直接对配置表、属性配置表进行新增操作
        // 新增配置表
        List<EndpointProperties> properties = endpointConfig.getEndpointProperties();
        endpointConfigMapper.insert(endpointConfig);
        // 新增属性配置表
        endpointPropertiesMapper.insertBatch(properties);
        return true;
    }

    /**
     * 批量更新端点的配置数据
     * (端点配置中的属性配置) 有ID的进行更新，没有ID的则进行新增，数据库中多出来的则进行删除
     *
     * @param endpointConfig 端点配置
     * @return 更新结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object updateEndpointConfigBatch(EndpointConfig endpointConfig) {
        // 首先通过配置查询其含有的属性配置数据（以此来确定哪些数据需要进行删除处理）
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(EndpointProperties::getConfigId, endpointConfig.getId());
        // 只取id字段用于判定
        queryWrapper.select(EndpointProperties::getId);
        // 查询配置数据
        List<EndpointProperties> endpointProperties = endpointPropertiesMapper.selectListByQuery(queryWrapper);
        // 从endpointConfig获取有ID的数据，从endpointProperties中剔除
        List<EndpointProperties> propertiesList = endpointConfig.getEndpointProperties();
        List<String> properties = propertiesList.stream().map(EndpointProperties::getId).toList();
        // 需要删除的ID
        List<String> willDeleteData = new ArrayList<>();
        for (EndpointProperties property : endpointProperties) {
            if (!properties.contains(property.getId())) {
                willDeleteData.add(property.getId());
            }
        }
        // 筛选出带id的数据进行修改
        List<EndpointProperties> updateList = propertiesList.stream().filter(property -> StringUtils.isNotBlank(property.getId())).toList();
        Db.updateEntitiesBatch(updateList);
        // 筛选出不带id的数据进行新增
        List<EndpointProperties> addList = propertiesList.stream().filter(property -> StringUtils.isBlank(property.getId())).toList();
        endpointPropertiesMapper.insertBatch(addList);
        // 进行删除操作
        endpointPropertiesMapper.deleteBatchByIds(willDeleteData);
        // 更新配置表
        endpointConfigMapper.update(endpointConfig);
        return true;
    }

    /**
     * 删除端点配置
     *
     * @param endpointConfigId 端点配置id
     * @return 删除结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object deleteEndpointConfig(String endpointConfigId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.where(EndpointProperties::getConfigId).eq(endpointConfigId);
        // 先删除属性配置表
        endpointPropertiesMapper.deleteByQuery(queryWrapper);
        // 删除端点配置表
        endpointConfigMapper.deleteById(endpointConfigId);
        return true;
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
