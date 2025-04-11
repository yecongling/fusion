package cn.fusion.engine.entity.project;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EndpointType
 * @Description 端点类型
 * @Author ycl
 * @Date 2024/11/23 17:46
 * @Version 1.0
 */
@Setter
@Getter
@Table(value = "t_engine_endpoint_type", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class EndpointType extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5893206708013699530L;

    // 类型id
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String id;

    // 类型名
    private String typeName;

    // 上级id
    private String parentId;

    @Column(ignore = true)
    private List<EndpointType> children;

    @Column(ignore = true)
    private List<EndpointConfig> endpointConfigs;

    public List<EndpointConfig> getEndpointConfigs() {
        if (endpointConfigs == null) {
            endpointConfigs = new ArrayList<>();
        }
        return endpointConfigs;
    }

    public List<EndpointType> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

}
