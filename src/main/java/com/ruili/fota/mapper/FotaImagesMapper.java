package com.ruili.fota.mapper;

import com.ruili.fota.meta.po.FotaImages;
import com.ruili.fota.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FotaImagesMapper extends BaseMapper<FotaImages> {
    int insert(FotaImages record);

    int insertSelective(FotaImages record);
}