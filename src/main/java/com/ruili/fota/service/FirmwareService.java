package com.ruili.fota.service;

import com.ruili.fota.meta.bo.ConfigBO;
import com.ruili.fota.meta.bo.ConfigResPO;
import com.ruili.fota.meta.bo.LoadProcessBO;
import com.ruili.fota.meta.po.FotaImages;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.meta.vo.OtaFileVO;
import org.apache.ibatis.javassist.NotFoundException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface FirmwareService {

    /**
     * 查询固件的列表信息
     *
     * @return
     */
    public List<OtaFileVO> queryFirmwareImages(String tenantId);

    /**
     * 写入设备固件信息，需要传入租户信息
     *
     * @return
     */
    public int insertFirmwareInfo(String firmwareId, String mcuType, String fileName, String firmwareVersion,
        String content, FotaUsers currentUser);

    /**
     * 设备进行固件烧写前下发配置信息
     *
     * @param configBO
     * @return
     * @throws IOException
     */
    public ConfigResPO configDownloadPatten(ConfigBO configBO, String tenantId) throws IOException, NotFoundException;

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
    public LoadProcessBO downloadFirmwareReport(String imei, String requestId, String tenantId)
        throws NotFoundException, IOException, ClassNotFoundException;

    /**
     * 固件删除
     *
     * @param firmwareId
     * @return
     */
    public boolean deleteFirmInfoByFirmId(String firmwareId, String tenantId) throws NotFoundException;

    /**
     * 通过imageId查询固件信息
     *
     * @param imageId
     * @return
     */
    public FotaImages selectImageByImageId(String imageId, String tenantId);

    /**
     * 通过imageId查询固件信息，给系统定时任务，不需要提供租户id
     *
     * @param imageId
     * @return
     */
    public FotaImages selectImageByImageIdForWatcher(String imageId);

}
