package com.ruili.fota.service.impl;

import com.ruili.fota.dao.entity.FotaProcessEntity;
import com.ruili.fota.dao.mapper.FotaLoadHistoryMapper;
import com.ruili.fota.dao.po.FotaLoadHistory;
import com.ruili.fota.netty.FotaProcessMap;
import com.ruili.fota.service.LoadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 查询管理设备更新的历史记录
 */
@Service
public class LoadHistoryServiceImpl implements LoadHistoryService {
    @Autowired
    private FotaLoadHistoryMapper fotaLoadHistoryMapper;

    @Override
    public int insertLoadHistoryByProcessEntity(String imei) {
        FotaProcessEntity entity = FotaProcessMap.get(imei);
        FotaLoadHistory history = new FotaLoadHistory(entity);
        fotaLoadHistoryMapper.insertSelective(history);
        return 0;
    }

    @Override
    public List<FotaLoadHistory> queryLoadHistory(String imei, String beginTime, String endTime) {
        Example queryLoadHistoryExample = new Example(FotaLoadHistory.class);
        Example.Criteria criteria = queryLoadHistoryExample.createCriteria();
        if (beginTime != null || beginTime != "") {
            criteria.andGreaterThanOrEqualTo("gmtCreate", beginTime);
        }
        if (endTime != null || endTime != "") {
            criteria.andLessThanOrEqualTo("gmtCreate", endTime);
        }
        if (imei != null || imei != "") {
            criteria.andEqualTo("imei", imei);
        }
        List<FotaLoadHistory> fotaLoadHistoryList = fotaLoadHistoryMapper.selectByExample(queryLoadHistoryExample);
        return fotaLoadHistoryList;
    }
}
