package com.ruili.fota.netty;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.meta.bo.ConfigBO;
import com.ruili.fota.meta.entity.FotaProcessEntity;
import io.netty.buffer.ByteBuf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: liangjingxiong
 * @date: 2019-05-03
 * @description:存储设备升级的过程Map
 */
public class FotaProcessMap {

    private static Map<String, FotaProcessEntity> map = new ConcurrentHashMap<String, FotaProcessEntity>();

    public static void add(String imei, FotaProcessEntity fotaProcessEntity) {
        map.put(imei, fotaProcessEntity);
    }

    public static FotaProcessEntity get(String imei) {
        return map.get(imei);
    }

    public static FotaProcessEntity initStateFotaProcessEntity(String imei,String requestId,String firmwareId, int totalPack, ByteBuf byteBuf, ConfigBO configBO) {
        FotaProcessEntity fotaProcessEntity = new FotaProcessEntity(imei, requestId,firmwareId, 0, totalPack, LoadStatusEnum.LOAD_ON_STATUS, byteBuf, configBO);
        add(imei, fotaProcessEntity);
        return get(imei);
    }

    //移除一条通道数据
    public static String remove(FotaProcessEntity fotaProcessEntity) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == fotaProcessEntity) {
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
