package cn.fusion.engine.camel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName FlowNode
 * @Description 执行流程中的每一个节点，包含节点的基础信息、配置信息
 * @Author ycl
 * @Date 2025/4/28 09:09
 * @Version 1.0
 **/
@Data
public class FlowNode implements Serializable {

    // 节点ID
    private Long id;

    // 节点名称
    private String name;

    // 节点类型
    private String type;

    // 节点配置信息
    private Map<String, Object> config;
}
