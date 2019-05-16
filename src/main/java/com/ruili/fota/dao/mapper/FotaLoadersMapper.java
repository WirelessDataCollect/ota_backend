package com.ruili.fota.dao.mapper;

import com.ruili.fota.dao.po.FotaLoaders;
import com.ruili.fota.dao.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FotaLoadersMapper extends BaseMapper<FotaLoaders> {
    int insert(FotaLoaders record);

    int insertSelective(FotaLoaders record);
}