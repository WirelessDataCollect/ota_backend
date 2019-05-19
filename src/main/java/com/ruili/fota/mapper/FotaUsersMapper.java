package com.ruili.fota.mapper;

import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FotaUsersMapper extends BaseMapper<FotaUsers> {
    int insert(FotaUsers record);

    int insertSelective(FotaUsers record);
}