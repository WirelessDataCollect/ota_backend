package com.ruili.fota.dao.mapper;

import com.ruili.fota.dao.po.FotaUsers;
import com.ruili.fota.dao.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FotaUsersMapper extends BaseMapper<FotaUsers> {
    int insert(FotaUsers record);

    int insertSelective(FotaUsers record);
}