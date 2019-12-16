package com.ruili.fota.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruili.fota.common.DateTools;
import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.mapper.FotaLoadHistoryMapper;
import com.ruili.fota.mapper.FotaLoadersMapper;
import com.ruili.fota.meta.entity.FotaProcessEntity;
import com.ruili.fota.meta.po.FotaLoadHistory;
import com.ruili.fota.meta.po.FotaLoaders;
import com.ruili.fota.meta.vo.OtaHistoryVO;
import com.ruili.fota.netty.FotaProcessMap;
import com.ruili.fota.service.LoadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 查询管理设备更新的历史记录
 */
@Service
public class LoadHistoryServiceImpl implements LoadHistoryService {
    @Autowired
    private FotaLoadHistoryMapper fotaLoadHistoryMapper;
    @Autowired
    private FotaLoadersMapper fotaLoadersMapper;

    @Override
    public int insertLoadHistoryByLoadStatus(String imei, LoadStatusEnum loadStatusEnum) {
        FotaProcessEntity entity = FotaProcessMap.get(imei);
        entity.setStatusEnum(loadStatusEnum);
        //计入结束时间
        entity.setEndTime(DateTools.currentTime());
        //清除设备的表的requestId内容
        Example example = new Example(FotaLoaders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("imei", imei);
        FotaLoaders loaders = fotaLoadersMapper.selectOneByExample(example);
        loaders.setRequestId(null);
        fotaLoadersMapper.updateByExample(loaders, example);
        FotaLoadHistory history = new FotaLoadHistory(entity);
        return fotaLoadHistoryMapper.insertSelective(history);
    }

    @Override
    public List<OtaHistoryVO> queryLoadHistory(String imei, String beginTime, String endTime, String tenantId) {
        Example queryLoadHistoryExample = new Example(FotaLoadHistory.class);
        Example.Criteria criteria = queryLoadHistoryExample.createCriteria();
        if (!StringUtils.isEmpty(beginTime)) {
            criteria.andGreaterThanOrEqualTo("gmtcreate", beginTime);
        }
        if (!StringUtils.isEmpty(endTime)) {
            criteria.andLessThanOrEqualTo("gmtcreate", endTime);
        }
        if (!StringUtils.isEmpty(imei)) {
            criteria.andLike("imei", "%" + imei + "%");
        }
        if (!StringUtils.isEmpty(tenantId)) {
            criteria.andLike("tenantId", "%" + tenantId + "%");
        }
        List<FotaLoadHistory> fotaLoadHistoryList = fotaLoadHistoryMapper.selectByExample(queryLoadHistoryExample);
        List<OtaHistoryVO> otaHistoryVOList = new ArrayList<>();
        for (FotaLoadHistory history : fotaLoadHistoryList) {
            otaHistoryVOList.add(new OtaHistoryVO(history));
        }
        return otaHistoryVOList;
    }
}
