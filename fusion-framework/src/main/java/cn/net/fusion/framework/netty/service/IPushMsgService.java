package cn.net.fusion.framework.netty.service;

/**
 * @ClassName IPushMsgService
 * @Description 消息推送服务
 * @Author ycl
 * @Date 2024/11/4 17:47
 * @Version 1.0
 */
public interface IPushMsgService {

    /**
     * 推送消息给指定用户
     *
     * @param userId 用户id
     * @param msg    消息
     */
    void pushMsgToOne(String userId, String msg);

    /**
     * 推送给所有用户
     *
     * @param msg 消息
     */
    void pushMsgToAll(String msg);
}
