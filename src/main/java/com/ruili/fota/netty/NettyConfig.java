package com.ruili.fota.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyConfig {
    @Bean
    public IdleStateHandler myIdleStateHandler() {
        return new IdleStateHandler(30, 0, 0);
    }

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