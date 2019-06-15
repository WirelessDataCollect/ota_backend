package com.ruili.fota.netty;

import io.netty.buffer.ByteBuf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* @author: liangjingxiong
* @date: 2019-06-14
* @description:存储固件的map
*/
public class FirmwareBufMap {

    private static Map<String, ByteBuf> map = new ConcurrentHashMap<String, ByteBuf>();

    public static void add(String imei, ByteBuf byteBuf) {
        map.put(imei, byteBuf);
    }

    public static ByteBuf get(String imei) {
        return map.get(imei);
    }

    //移除一条通道数据
    public static String remove(ByteBuf byteBuf) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == byteBuf) {
                String key = (String) entry.getKey();
                map.remove(entry.getKey());
                return key;
            }
        }
        return null;
    }

    public static String removeByImei(String imei) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == map.get(imei)) {
                String key = (String) entry.getKey();
                map.remove(entry.getKey());
                return key;
            }
        }
        return null;
    }

    public static Map getMap() {
        return map;
    }
}
