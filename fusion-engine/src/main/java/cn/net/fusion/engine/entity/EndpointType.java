package cn.net.fusion.engine.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName EndpointType
 * @Description 端点类型
 * @Author ycl
 * @Date 2024/11/23 17:46
 * @Version 1.0
 */
@TableName("t_engine_endpoint_type")
public class EndpointType implements Serializable {

    @Serial
    private static final long serialVersionUID = 5893206708013699530L;

    // 类型id
    private Integer typeId;

    // 类型名
    private String typeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
