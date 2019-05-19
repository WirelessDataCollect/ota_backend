package com.ruili.fota.mapper;

import com.ruili.fota.meta.po.FotaLoaders;
import com.ruili.fota.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FotaLoadersMapper extends BaseMapper<FotaLoaders> {
    int insert(FotaLoaders record);

    int insertSelective(FotaLoaders record);
}