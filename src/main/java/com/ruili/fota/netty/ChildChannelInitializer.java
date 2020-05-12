package com.ruili.fota.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
* @author: liangjingxiong
* @date: 2019-04-09
* @description:
*/
@Component
public class ChildChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ServerHandler serverHandler;

    /**
     * 解码器
     */
    @Autowired
    private StringDecoder stringDecoder;

    /**
     * 心跳时间
     */
    @Value("${netty.heartBeatSecond}")
    private long heartBeatSecond;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //添加读超时的处理Handler
        pipeline.addLast(new IdleStateHandler((long) (heartBeatSecond*1.5), 0, 0, TimeUnit.SECONDS));
        //添加logger的handler，打印连接信息
        pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
        //添加netty的String解码器
        pipeline.addLast(stringDecoder);
        //添加自定义的服务消息handler
        pipeline.addLast(serverHandler);
    }
}
