package com.ruili.fota.dao.mapper;

import com.ruili.fota.dao.po.FotaLoadHistory;
import com.ruili.fota.dao.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FotaLoadHistoryMapper extends BaseMapper<FotaLoadHistory> {
    int insert(FotaLoadHistory record);

    int insertSelective(FotaLoadHistory record);
}