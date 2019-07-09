package com.ruili.fota.mapper;

import com.ruili.fota.meta.po.FotaLoadHistory;
import com.ruili.fota.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FotaLoadHistoryMapper extends BaseMapper<FotaLoadHistory> {
    @Override
    int insert(FotaLoadHistory record);

    @Override
    int insertSelective(FotaLoadHistory record);
}