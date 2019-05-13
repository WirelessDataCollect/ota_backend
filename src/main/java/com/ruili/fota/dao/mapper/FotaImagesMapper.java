package com.ruili.fota.dao.mapper;

import com.ruili.fota.dao.po.FotaImages;

public interface FotaImagesMapper {
    int insert(FotaImages record);

    int insertSelective(FotaImages record);
}