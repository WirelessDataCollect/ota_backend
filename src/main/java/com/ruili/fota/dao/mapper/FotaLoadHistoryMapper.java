package com.ruili.fota.dao.mapper;

import com.ruili.fota.dao.po.FotaLoadHistory;

public interface FotaLoadHistoryMapper {
    int insert(FotaLoadHistory record);

    int insertSelective(FotaLoadHistory record);
}