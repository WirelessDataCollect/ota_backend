package com.ruili.fota.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class NettyConfig {

//    @Value("${netty.heartBeatSecond}")
//    private long heartBeatSecond;
//
//    @Bean
//    public IdleStateHandler myIdleStateHandler() {
//        return new IdleStateHandler((long) (heartBeatSecond*1.5), 0, 0, TimeUnit.SECONDS);
//    }

    @Bean
    public NioEventLoopGroup myNioEventLoopGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public ServerBootstrap myServerBootstrap() {
        return new ServerBootstrap();
    }

    @Bean
    public StringDecoder myStringDecoder() {
        return new StringDecoder();
    }
}