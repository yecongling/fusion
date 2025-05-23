package cn.fusion.system.event;

import cn.fusion.framework.enums.SysOperation;
import cn.fusion.framework.event.BaseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName SystemConsumer
 * @Description system模块的消息消费者
 * @Author 叶丛林
 * @Date 2024/11/4 21:30
 * @Version 1.0
 **/
@Component(value = "system_consumer")
@Async // 允许异步执行
public class SystemConsumer implements ApplicationListener<BaseEvent<Object>> {

    // 定义日志
    private static final Logger logger = LoggerFactory.getLogger(SystemConsumer.class);

    @Override
    public void onApplicationEvent(BaseEvent<Object> event) {
        // 获取事件操作类型
        SysOperation operation = event.getOperation();
        Object data = event.getData();
        switch (operation) {
            case LOGIN:
                System.out.println("登录操作");
                break;
            case LOGOUT:
                System.out.println("退出登录");
            default:
                break;
        }
        logger.info("SystemConsumer onApplicationEvent, the data is {}", data);
    }
}
