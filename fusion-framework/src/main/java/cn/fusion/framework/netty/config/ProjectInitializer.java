package cn.fusion.framework.netty.config;

import cn.fusion.framework.netty.handler.WebSocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @ClassName ProjectInitializer
 * @Description 项目初始化
 * @Author ycl
 * @Date 2024/11/4 11:54
 * @Version 1.0
 */
@Component
public class ProjectInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * websocket协议名
     */
    private static final String WEBSOCKET_PROTOCOL = "WebSocket";

    /**
     * websocket 路径
     */
    @Value("${websocket.netty.path:/webSocket}")
    String webSocketPath;
    WebSocketHandler webSocketHandler;

    @Autowired
    public void setWebSocketHandler(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    /**
     * 初始化channel
     *
     * @param socketChannel socket
     * @throws Exception exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 设置通道
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 流水线管道通道中的处理程序（Handler），用来处理业务
        // websocket协议本身是基于http协议的，所以这里也要使用http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(8192));
        pipeline.addLast(new WebSocketServerProtocolHandler(webSocketPath, WEBSOCKET_PROTOCOL, true, 65536 * 10));
        // 自定义handler，处理业务逻辑
        pipeline.addLast(webSocketHandler);
    }
}
