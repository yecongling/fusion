package cn.fusion.engine.camel.core;

import cn.fusion.engine.camel.model.FlowNode;
import org.apache.camel.Exchange;

/**
 * @ClassName IActionExecutor
 * @Description 节点执行器
 * 节点执行器，用于执行节点的具体操作
 * 例如：消息过滤、消息转换、消息路由等
 * 节点执行器需要实现该接口，实现具体的操作
 * 节点执行器需要注册到Spring容器中，以便于在路由中使用
 * @Author ycl
 * @Date 2025/4/28 09:03
 * @Version 1.0
 **/
public interface IActionExecutor {

    /**
     * 判断执行器是否支持该操作
     * @param actionType 操作类型
     * @return true:支持，false:不支持
     */
    boolean support(String actionType);

    /**
     * 执行操作
     * @param node 节点
     * @param exchange 消息交换对象
     */
    void execute(FlowNode node, Exchange exchange);
}
