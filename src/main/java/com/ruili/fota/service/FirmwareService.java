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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface FirmwareService {

    /**
     * 查询固件的列表信息
     *
     * @return
     */
    public List<OtaFileVO> queryFirmwareImages(@NotBlank String tenantId);

    /**
     * 写入设备固件信息，需要传入租户信息
     *
     * @return
     */
    public int insertFirmwareInfo(@NotBlank String firmwareId, @NotBlank String mcuType, @NotBlank String fileName,
        @NotBlank String firmwareVersion,
        @NotBlank String content, @NotNull FotaUsers currentUser);

    /**
     * 设备进行固件烧写前下发配置信息
     *
     * @param configBO
     * @return
     * @throws IOException
     */
    public ConfigResPO configDownloadPatten(@NotNull ConfigBO configBO, @NotBlank String tenantId)
        throws IOException, NotFoundException;

    /**
     * 下发固件给设备
     *
     * @param imei
     * @param packNum，需要知道当前请求下发的是哪个包
     * @return
     */
    public void downloadFirmware(@NotBlank String imei, @NotNull int packNum) throws NoSuchAlgorithmException;

    /**
     * 固件下发结果查询
     *
     * @param imei
     * @return
     */
    public LoadProcessBO downloadFirmwareReport(@NotBlank String imei,@NotBlank String requestId,@NotBlank String tenantId)
        throws NotFoundException, IOException, ClassNotFoundException;

    /**
     * 批量删除固件
     *
     * @param firmwareId
     * @param tenantId
     * @return
     * @throws NotFoundException
     */
    public boolean batchDeleteFirmInfoByFirmId(@NotEmpty List<String> firmwareId, @NotBlank String tenantId) throws NotFoundException;

    /**
     * 通过imageId查询固件信息
     *
     * @param imageId
     * @return
     */
    public FotaImages selectImageByImageId(@NotBlank String imageId,@NotBlank String tenantId);

    /**
     * 通过imageId查询固件信息，给系统定时任务，不需要提供租户id
     *
     * @param imageId
     * @return
     */
    public FotaImages selectImageByImageIdForWatcher(@NotBlank String imageId);

}
