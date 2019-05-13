package com.ruili.fota.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Component;

@Component
public class PubToDevice {

    /**
     * 下发String消息给设备
     * @param imei
     * @param msg
     * @return
     */
    public boolean pubToDevice(String imei, String msg) {
        boolean pubResult = false;
        SocketChannel sc = NettyChannelMap.get(imei);

        if (sc != null) {
            ByteBuf responseBuf = sc.alloc().buffer(4 * msg.length());
            responseBuf.writeBytes(msg.getBytes());
            sc.writeAndFlush(responseBuf);
            pubResult = true;
        }

        return pubResult;
    }

    /**
     * 下发Object数据给设备
     * @param imei
     * @param obj
     * @return
     */
    public boolean pubToDevice(String imei, Object obj) {
        String msg = obj.toString();
        boolean pubResult = pubToDevice(imei,msg);
        return pubResult;
    }

}
