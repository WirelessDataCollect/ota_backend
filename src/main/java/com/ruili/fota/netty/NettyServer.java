package com.ruili.fota.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: liangjingxiong
 * @date: 2019-04-09
 * @description:
 * NettyServer使用到了定义的ChildChannel pipline
 * 服务器启动的相关配置
 * 将Channel、pipline和EventLoop结合的方法，使用Bootstrap引导类，对启动配置进行引导
 */

@Component
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Autowired
    private InitNetty initNetty;
    private NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private NioEventLoopGroup workGroup = new NioEventLoopGroup();
    private ServerBootstrap serverBootstrap = new ServerBootstrap();

    @Autowired
    private ChildChannelInitializer childChannelInitializer;

    private Channel channel;

    /**
     * Netty的Server端需要两个EventLoopGroup，一个用于监听本地绑定的host，一个用于处理客户端连接Channel的Group
     * 在连接之前，必须要完成的配置有group、channel、handler
     * handler在初始化时就会执行，而childHandler会在客户端成功connect后才执行
     */
    public void start() {
        try {
            serverBootstrap
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, initNetty.isNodelay())
                    .option(ChannelOption.SO_BACKLOG, initNetty.getBacklog())
                    .childHandler(childChannelInitializer);
            /**
             * bind的channel是ServerChannel，此Channel管理多个子channel
             */
            channel = serverBootstrap.bind(initNetty.getPort()).addListener(future -> {
                if (future.isSuccess()) {
                    logger.info("端口: " + initNetty.getPort() + " 绑定成功!");
                } else {
                    logger.error("端口: " + initNetty.getPort() + " 绑定失败!");
                }
            }).sync().channel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 在结束时进行调用的函数
     * @throws Exception
     */
    public void stop() throws Exception {
        channel.closeFuture().sync();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}
