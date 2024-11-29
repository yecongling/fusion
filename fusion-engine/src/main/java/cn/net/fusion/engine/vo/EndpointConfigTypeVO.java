package cn.net.fusion.engine.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @ClassName EndpointConfigTypeVO
 * @Description 端点配置和类型的联合实体对象，用于页面上的左边那颗树结构
 * @Author ycl
 * @Date 2024/11/26 09:39
 * @Version 1.0
 */
public class EndpointConfigTypeVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3577463857341920571L;
    // 分类id
    private Integer typeId;
    // 分类名称
    private String typeName;

    // 上级分类
    private String parentId;

    // 配置id
    private String configId;

    // 配置名称
    private String configName;


}
