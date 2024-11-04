package cn.net.fusion.framework.netty.service;

import cn.net.fusion.framework.netty.config.NettyConfig;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName PushMsgServiceImpl
 * @Description 消息推送服务实现类
 * @Author ycl
 * @Date 2024/11/4 17:52
 * @Version 1.0
 */
@Service
public class PushMsgServiceImpl implements IPushMsgService {

    /**
     * 推送消息给指定用户
     *
     * @param userId 用户id
     * @param msg    消息
     */
    @Override
    public void pushMsgToOne(String userId, String msg) {
        if (StringUtils.isBlank(userId)) {
            return;
        }
        Channel channel = NettyConfig.getChannel(userId);
        if (Objects.isNull(channel)) {
            return;
        }
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    /**
     * 推送给所有用户
     *
     * @param msg 消息
     */
    @Override
    public void pushMsgToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
