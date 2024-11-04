package cn.net.fusion.framework.netty.config;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName NettyConfig
 * @Description 管理全局的channel以及用户对应的channel（推送消息）
 * @Author ycl
 * @Date 2024/11/4 11:50
 * @Version 1.0
 */
public class NettyConfig {

    /**
     * 定义全局单例的channel组，管理所有的channel
     */
    private static volatile ChannelGroup channelGroup = null;

    /**
     * 存放请求ID和channel的对应关系
     */
    private static volatile ConcurrentHashMap<String, Channel> channelMap = null;

    /**
     * 定义两把锁
     */
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    /**
     * 获取channel组
     *
     * @return channelGroup
     */
    public static ChannelGroup getChannelGroup() {
        if (null == channelGroup) {
            synchronized (lock1) {
                if (null == channelGroup) {
                    channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
                }
            }
        }
        return channelGroup;
    }

    /**
     * 获取channel的map
     *
     * @return map
     */
    public static ConcurrentHashMap<String, Channel> getChannelMap() {
        if (null == channelMap) {
            synchronized (lock2) {
                if (null == channelMap) {
                    channelMap = new ConcurrentHashMap<>();
                }
            }
        }
        return channelMap;
    }

    /**
     * 根据用户ID获取对应的channel
     *
     * @param userId 用户id
     * @return channel
     */
    public static Channel getChannel(String userId) {
        if (null == channelMap) {
            return getChannelMap().get(userId);
        }
        return channelMap.get(userId);
    }
}
