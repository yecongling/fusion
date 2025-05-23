package cn.fusion.system.entity;

import cn.fusion.framework.config.EntityInsertListener;
import cn.fusion.framework.config.EntityUpdateListener;
import cn.fusion.framework.core.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName SysDictItem
 * @Description 字典条目
 * @Author 叶丛林
 * @Date 2025/3/31 20:30
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "t_sys_dict", onInsert = EntityInsertListener.class, onUpdate = EntityUpdateListener.class)
public class SysDictItem extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    // 所属字典ID
    private Long dictId;

    // 字典文本
    private String itemText;

    // 字典值
    private String itemValue;

    // 字典颜色
    private String itemColor;

    // 字典排序
    private Integer itemSort;

    // 字典备注
    private String remark;

    // 字典状态
    private Integer status;

    // 默认值
    private Boolean defaultFlag;

}
