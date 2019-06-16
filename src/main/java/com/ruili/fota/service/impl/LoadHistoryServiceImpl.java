package com.ruili.fota.service.impl;

import com.ruili.fota.common.DateTools;
import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.meta.entity.FotaProcessEntity;
import com.ruili.fota.mapper.FotaLoadHistoryMapper;
import com.ruili.fota.meta.po.FotaLoadHistory;
import com.ruili.fota.meta.vo.OtaHistoryVO;
import com.ruili.fota.netty.FotaProcessMap;
import com.ruili.fota.service.LoadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询管理设备更新的历史记录
 */
@Service
public class LoadHistoryServiceImpl implements LoadHistoryService {
    @Autowired
    private FotaLoadHistoryMapper fotaLoadHistoryMapper;

    @Override
    public int insertLoadHistoryByLoadStatus(String imei, LoadStatusEnum loadStatusEnum) {
        FotaProcessEntity entity = FotaProcessMap.get(imei);
        entity.setStatusEnum(loadStatusEnum);
        //计入结束时间
        entity.setEndTime(DateTools.currentTime());
        FotaLoadHistory history = new FotaLoadHistory(entity);
        return fotaLoadHistoryMapper.insertSelective(history);
    }

    @Override
    public List<OtaHistoryVO> queryLoadHistory(String imei, String beginTime, String endTime) {
        Example queryLoadHistoryExample = new Example(FotaLoadHistory.class);
        Example.Criteria criteria = queryLoadHistoryExample.createCriteria();
        if (beginTime != null || beginTime != "") {
            criteria.andGreaterThanOrEqualTo("gmtcreate", beginTime);
        }
        if (endTime != null || endTime != "") {
            criteria.andLessThanOrEqualTo("gmtcreate", endTime);
        }
        if (imei != null || imei != "") {
            criteria.andLike("imei", imei);
        }
        List<FotaLoadHistory> fotaLoadHistoryList = fotaLoadHistoryMapper.selectByExample(queryLoadHistoryExample);
        List<OtaHistoryVO> otaHistoryVOList = new ArrayList<>();
        for (FotaLoadHistory history : fotaLoadHistoryList) {
            otaHistoryVOList.add(new OtaHistoryVO(history));
        }
        return otaHistoryVOList;
    }
}
