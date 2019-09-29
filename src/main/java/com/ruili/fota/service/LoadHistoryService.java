package com.ruili.fota.service;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.meta.po.FotaLoadHistory;
import com.ruili.fota.meta.vo.OtaHistoryVO;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface LoadHistoryService {

    /**
     * 更新设备的历史，通过imei号将Map中的对象
     *
     * @param imei
     * @return
     */
    public int insertLoadHistoryByLoadStatus(@NotBlank String imei,@NotNull LoadStatusEnum loadStatusEnum);

    /**
     * 查询设备升级历史记录，支持imei前后模糊查询
     *
     * @param imei
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<OtaHistoryVO> queryLoadHistory( String imei, String beginTime, String endTime, String tenantId);
}
