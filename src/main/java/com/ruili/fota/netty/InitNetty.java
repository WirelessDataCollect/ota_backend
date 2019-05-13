package com.ruili.fota.netty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author: liangjingxiong
 * @date: 2019-04-09
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "netty")
public class InitNetty {
    private int port;
    private int backlog;
    private boolean nodelay;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getBacklog() {
        return backlog;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }

    public boolean isNodelay() {
        return nodelay;
    }

    public void setNodelay(boolean nodelay) {
        this.nodelay = nodelay;
    }
}
