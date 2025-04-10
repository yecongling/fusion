package cn.fusion.system.entity;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName SysDict
 * @Description 数据字典实体类
 * @Author ycl
 * @Date 2025/3/27 13:37
 * @Version 1.0
 **/
@Setter
@Getter
@Table(value = "t_sys_dict", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysDict extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 字典编码
     */
    private String dictCode;

    // 字典名称
    private String dictName;

    // 字典类型
    private Integer type;

    // 备注
    private String remark;

    // 删除标记
    @Column(isLogicDelete = true)
    private Boolean delFlag;

}
