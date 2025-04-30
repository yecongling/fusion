package cn.fusion.engine.camel.processor;

import cn.fusion.engine.camel.core.IActionExecutor;
import cn.fusion.engine.camel.model.FlowNode;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * @ClassName MsgFilter
 * @Description 消息过滤器
 * @Author ycl
 * @Date 2025/2/8 16:52
 * @Version 1.0
 */
@Component
public class MsgFilter implements IActionExecutor {

    /**
     * 判断执行器是否支持该操作
     *
     * @param actionType 操作类型
     * @return true:支持，false:不支持
     */
    @Override
    public boolean support(String actionType) {
        return false;
    }

    /**
     * 执行操作
     *
     * @param node     节点
     * @param exchange 消息交换对象
     */
    @Override
    public void execute(FlowNode node, Exchange exchange) {

    }
}
