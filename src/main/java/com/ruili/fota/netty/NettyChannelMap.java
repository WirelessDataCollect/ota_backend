package com.ruili.fota.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: liangjingxiong
 * @date: 2019-04-22
 * @description: 将设备的imei号和socketChannel存入Mapper中来进行管理与消息的主动下发
 */
public class NettyChannelMap {
    private static Map<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();

    public static void add(String clientId, SocketChannel socketChannel) {
        map.put(clientId, socketChannel);
    }

    public static SocketChannel get(String clientId) {
        return map.get(clientId);
    }

    /**
     * 移除一条通道数据
     * @param socketChannel
     * @return
     */
    public static String remove(SocketChannel socketChannel) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == socketChannel) {
                String key = (String) entry.getKey();
                map.remove(entry.getKey());
                return key;
            }
        }
        return null;
    }

    /**
     * 移除一条通道数据
     * @param ctx
     * @return
     */
    public static String remove(ChannelHandlerContext ctx) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == ctx) {
                String key = (String) entry.getKey();
                map.remove(entry.getKey());
                return key;
            }
        }
        return null;
    }

    /**
     * 根据通道信息获取imei号，适用于终端不上报imei号的场景
     * @param socketChannel
     * @return
     */
    public static String getImei(SocketChannel socketChannel) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == socketChannel) {
                String key = (String) entry.getKey();
                return key;
            }
        }
        return null;
    }

    public static Map getMap() {
        return map;
    }
}
