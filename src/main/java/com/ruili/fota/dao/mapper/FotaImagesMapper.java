package com.ruili.fota.dao.mapper;

import com.ruili.fota.dao.po.FotaImages;
import com.ruili.fota.dao.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FotaImagesMapper extends BaseMapper<FotaImages> {
    int insert(FotaImages record);

    int insertSelective(FotaImages record);
}