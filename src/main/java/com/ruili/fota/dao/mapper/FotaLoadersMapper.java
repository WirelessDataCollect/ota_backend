package com.ruili.fota.dao.mapper;

import com.ruili.fota.dao.po.FotaLoaders;

public interface FotaLoadersMapper {
    int insert(FotaLoaders record);

    int insertSelective(FotaLoaders record);
}