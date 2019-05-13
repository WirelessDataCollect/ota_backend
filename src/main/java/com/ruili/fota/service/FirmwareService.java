package com.ruili.fota.service;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.dao.bo.ConfigBO;
import com.ruili.fota.dao.bo.ConfigResPO;
import com.ruili.fota.dao.bo.LoadProcessBO;
import com.ruili.fota.netty.pk.ConfigPK;
import com.ruili.fota.dao.entity.FotaProcessEntity;
import com.ruili.fota.dao.po.FotaImages;
import com.ruili.fota.dao.po.FotaLoadHistory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface FirmwareService {

    /**
     * 写入设备固件信息
     *
     * @return
     */
    public int insertFirmwareInfo(FotaImages fotaImages);

    /**
     * 设备进行固件烧写前下发配置信息
     *
     * @param configBO
     * @return
     * @throws IOException
     */
    public ConfigResPO configDownloadPatten(ConfigBO configBO) throws IOException;

    /**
     * 下发固件给设备
     *
     * @param imei
     * @param packNum，需要知道当前请求下发的是哪个包
     * @return
     */
    public void downloadFirmware(String imei, int packNum) throws NoSuchAlgorithmException;

    /**
     * 固件下发结果查询
     *
     * @param imei
     * @return
     */
    public LoadProcessBO downloadFirmwareReport(String imei, String requestId);


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
