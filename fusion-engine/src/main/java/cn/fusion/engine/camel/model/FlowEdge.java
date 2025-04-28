package cn.fusion.engine.camel.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName FlowEdge
 * @Description 节点连线，用于描述节点之间的关系
 * @Author ycl
 * @Date 2025/4/28 09:12
 * @Version 1.0
 **/
@Data
public class FlowEdge implements Serializable {

    // 连线ID
    private String id;

    // 源节点ID
    private String source;

    // 目标节点ID
    private String target;
}
