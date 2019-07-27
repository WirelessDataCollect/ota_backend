package com.ruili.fota.netty;

import com.ruili.fota.common.DateTools;
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

    /**
     * 危险，不推荐，没有做资源隔离
     *
     * @param imei
     * @return
     */
    public static FotaProcessEntity get(String imei) {
        return map.get(imei);
    }

    /**
     * 带租户校验的查询过程
     *
     * @param imei
     * @return
     */
    public static FotaProcessEntity getByTenantId(String imei, String tenantId) {
        FotaProcessEntity entity = map.get(imei);
        if (entity == null) {
            return null;
        }
        if (!entity.getTenantId().equals(tenantId)) {
            return null;
        }
        return entity;
    }

    /**
     * 初始化processEntity，需要传入租户信息，进行租户级别的资源隔离
     *
     * @param imei
     * @param requestId
     * @param firmwareId
     * @param totalPack
     * @param configBO
     * @param tenantId
     * @return
     */
    public static FotaProcessEntity initStateFotaProcessEntity(String imei, String requestId, String firmwareId,
        int totalPack, ConfigBO configBO, String tenantId) {
        FotaProcessEntity fotaProcessEntity = new FotaProcessEntity(imei, requestId, firmwareId, 0, totalPack,
            LoadStatusEnum.CONFIG_NO_STATUS, configBO, null, null, DateTools.currentTime(), tenantId);
        add(imei, fotaProcessEntity);
        return get(imei);
    }

    /**
     * 更新processEntity，需要传入租户信息，进行资源隔离
     *
     * @param imei
     * @param requestId
     * @param firmwareId
     * @param totalPack
     * @param configBO
     * @param tenantId
     * @return
     */
    public static FotaProcessEntity updateFotaProcessEntity(String imei, String requestId, String firmwareId,
        int totalPack, ConfigBO configBO, String tenantId) {
        removeByImei(imei);
        FotaProcessEntity fotaProcessEntity = new FotaProcessEntity(imei, requestId, firmwareId, 0, totalPack,
            LoadStatusEnum.CONFIG_NO_STATUS, configBO, null, null, DateTools.currentTime(), tenantId);
        add(imei, fotaProcessEntity);
        return get(imei);
    }

    //移除一条通道数据
    public static String remove(FotaProcessEntity fotaProcessEntity) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == fotaProcessEntity) {
                String key = (String)entry.getKey();
                map.remove(entry.getKey());
                return key;
            }
        }
        return null;
    }

    /**
     * 危险，没有进行资源校验，tenantId的判断
     *
     * @param imei
     * @return
     */
    public static String removeByImei(String imei) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == map.get(imei)) {
                String key = (String)entry.getKey();
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
