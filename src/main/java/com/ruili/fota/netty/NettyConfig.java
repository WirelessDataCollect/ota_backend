package com.ruili.fota.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.ResourceLeakDetector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class NettyConfig {

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