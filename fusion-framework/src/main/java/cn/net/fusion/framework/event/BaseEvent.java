package cn.net.fusion.framework.event;

import cn.net.fusion.framework.enums.SysOperation;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * @ClassName BaseEvent
 * @Description 事件基础类
 * @Author 叶丛林
 * @Date 2024/11/4 21:23
 * @Version 1.0
 **/
public class BaseEvent<T> extends ApplicationEvent implements ResolvableTypeProvider {

    // 事件的操作数据
    private T data;

    // 事件的操作类型
    private SysOperation operation;

    public BaseEvent(T data, SysOperation operation) {
        super(data);
        this.data = data;
        this.operation = operation;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SysOperation getOperation() {
        return operation;
    }

    public void setOperation(SysOperation operation) {
        this.operation = operation;
    }

    /**
     * 解析类型（在运行期可通过这个方法获取到真正的数据类型，避免了编译期的范型擦除）
     *
     * @return 解析类型
     */
    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getData()));
    }
}
