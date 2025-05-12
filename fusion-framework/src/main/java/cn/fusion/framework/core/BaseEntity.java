package cn.fusion.framework.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName BaseEntity
 * @Description 所有bean的基类，存储创建人、创建时间、更新人、更新时间
 * @Author ycl
 * @Date 2024/12/14 10:49
 * @Version 1.0
 */
@Data
public class BaseEntity {
    // 创建人
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    // 更新人
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateBy;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
