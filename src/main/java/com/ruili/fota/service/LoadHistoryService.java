package com.ruili.fota.service;

import com.ruili.fota.dao.po.FotaLoadHistory;

import java.util.List;

public interface LoadHistoryService {

    /**
     * 更新设备的历史，通过imei号将Map中的对象
     * @param imei
     * @return
     */
    public int insertLoadHistoryByProcessEntity(String imei);

    /**
     * 查询设备升级历史记录
     *
     * @param imei
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<FotaLoadHistory> queryLoadHistory(String imei, String beginTime, String endTime);
}
