package cn.fusion.framework.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName Consumer
 * @Description 消费消息
 * @Author 叶丛林
 * @Date 2024/11/4 21:27
 * @Version 1.0
 **/
@Component("framework_consumer")
@Async  // 允许异步执行
public class BaseConsumer implements ApplicationListener<BaseEvent<Object>> {
    // 定义日志
    private static final Logger logger = LoggerFactory.getLogger(BaseConsumer.class);

    /**
     * @param event 事件传输的对象
     */
    @Override
    public void onApplicationEvent(BaseEvent event) {
        // 打印日志
        logger.info("Spring事件驱动模型-接收消息：{}", event);
    }
}
