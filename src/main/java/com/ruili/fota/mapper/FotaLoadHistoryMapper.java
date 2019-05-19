package com.ruili.fota.mapper;

import com.ruili.fota.meta.po.FotaLoadHistory;
import com.ruili.fota.meta.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FotaLoadHistoryMapper extends BaseMapper<FotaLoadHistory> {
    int insert(FotaLoadHistory record);

    int insertSelective(FotaLoadHistory record);
}