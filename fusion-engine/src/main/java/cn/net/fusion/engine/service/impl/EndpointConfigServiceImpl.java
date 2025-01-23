package cn.net.fusion.engine.service.impl;

import cn.net.fusion.engine.entity.EndpointConfig;
import cn.net.fusion.engine.entity.EndpointConfigProperty;
import cn.net.fusion.engine.entity.EndpointType;
import cn.net.fusion.engine.mapper.EndpointConfigMapper;
import cn.net.fusion.engine.mapper.EndpointPropertyMapper;
import cn.net.fusion.engine.mapper.EndpointTypeMapper;
import cn.net.fusion.engine.service.IEndpointConfigService;
import cn.net.fusion.framework.core.SysOpr;
import cn.net.fusion.framework.exception.BusinessException;
import cn.net.fusion.framework.utils.ServletUtils;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName EndpointConfigServiceImpl
 * @Description 端点类型配置业务实现
 * @Author ycl
 * @Date 2024/11/23 17:57
 * @Version 1.0
 */
@Service
public class EndpointConfigServiceImpl implements IEndpointConfigService {

    // 数据库操作接口
    private final EndpointTypeMapper endpointTypeMapper;
    private final EndpointConfigMapper endpointConfigMapper;
    private final EndpointPropertyMapper endpointPropertyMapper;
    private final ServletUtils servletUtils;

    @Autowired
    public EndpointConfigServiceImpl(EndpointTypeMapper endpointTypeMapper,
                                     EndpointConfigMapper endpointConfigMapper,
                                     EndpointPropertyMapper endpointPropertyMapper,
                                     ServletUtils servletUtils) {
        this.endpointTypeMapper = endpointTypeMapper;
        this.endpointConfigMapper = endpointConfigMapper;
        this.endpointPropertyMapper = endpointPropertyMapper;
        this.servletUtils = servletUtils;
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
        queryWrapper.select(
                QueryMethods.column(EndpointType::getTypeName),
                QueryMethods.column(EndpointType::getId),
                QueryMethods.column(EndpointType::getParentId)
        );
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
        queryWrapper.select(
                QueryMethods.column(EndpointConfig::getId),
                QueryMethods.column(EndpointConfig::getConfigName),
                QueryMethods.column(EndpointConfig::getTypeId),
                QueryMethods.column(EndpointConfig::getIcon),
                QueryMethods.column(EndpointConfig::getSupportedMode),
                QueryMethods.column(EndpointConfig::isSupportRetry),
                QueryMethods.column(EndpointConfig::isStrategy)
        );
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
     * 删除分类ID
     *
     * @param typeId 分类ID
     * @return 删除结果
     */
    @Override
    public Boolean deleteEndpointConfigType(String typeId) {
        // 需要判定这个分类下面是否还有下级节点
        long count = endpointTypeMapper.selectCountByQuery(new QueryWrapper().eq(EndpointType::getParentId, typeId));
        if (count > 0) {
            throw new BusinessException("该分类下存在下级分类，无法删除！");
        }
        long configCount = endpointConfigMapper.selectCountByQuery(new QueryWrapper().eq(EndpointConfig::getTypeId, typeId));
        if (configCount > 0) {
            throw new BusinessException("该分类下存在端点配置数据，无法删除！");
        }
        int i = endpointTypeMapper.deleteById(typeId);
        return i > 0;
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
        List<EndpointConfigProperty> endpointProperties = endpointPropertyMapper.selectListByQuery(new QueryWrapper().eq(EndpointConfig::getConfig, id));
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
        List<EndpointConfigProperty> properties = endpointConfig.getEndpointProperties();
        endpointConfigMapper.insert(endpointConfig);
        // 新增属性配置表
        endpointPropertyMapper.insertBatch(properties);
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
        // 需要修改的数据填充更新时间和更新人（非mapper操作无法使用onUpdate监听）
        SysOpr sysOpr = servletUtils.getSysOpr();
        // 首先通过配置查询其含有的属性配置数据（以此来确定哪些数据需要进行删除处理）
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(EndpointConfigProperty::getConfigId, endpointConfig.getId());
        // 只取id字段用于判定
        queryWrapper.select(QueryMethods.column(EndpointConfigProperty::getId));
        // 查询存在的ID数据
        List<EndpointConfigProperty> endpointProperties = endpointPropertyMapper.selectListByQuery(queryWrapper);
        List<String> existingIds = endpointProperties.stream().map(EndpointConfigProperty::getId).toList();

        // 从endpointConfig获取有ID的数据，从endpointProperties中剔除
        List<EndpointConfigProperty> propertiesList = endpointConfig.getEndpointProperties();

        // 获取需要删除的ID（在数据库存在但传入的参数中不存在）
        List<String> willDeleteData = existingIds.stream()
                .filter(id -> propertiesList.stream().noneMatch(property -> id.equals(property.getId())))
                .collect(Collectors.toList());

        // 筛选出带id的数据进行修改
        List<EndpointConfigProperty> updateList = propertiesList.stream()
                .filter(property -> StringUtils.isNotBlank(property.getId()))
                .peek(property -> {
                    property.setUpdateBy(sysOpr.getUserId());
                    property.setUpdateTime(LocalDateTime.now());
                })
                .collect(Collectors.toList());

        // 筛选出不带id的数据进行新增
        List<EndpointConfigProperty> addList = propertiesList.stream()
                .filter(property -> StringUtils.isBlank(property.getId()))
                .collect(Collectors.toList());

        // 进行删除操作
        if (!willDeleteData.isEmpty()) {
            endpointPropertyMapper.deleteBatchByIds(willDeleteData);
        }

        // 执行批量更新
        if (!updateList.isEmpty()) {
            Db.updateEntitiesBatch(updateList);
        }

        // 执行新增
        if (!addList.isEmpty()) {
            endpointPropertyMapper.insertBatch(addList);
        }

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
        queryWrapper.where(EndpointConfigProperty::getConfigId).eq(endpointConfigId);
        // 先删除属性配置表
        endpointPropertyMapper.deleteByQuery(queryWrapper);
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
