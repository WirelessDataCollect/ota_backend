package com.ruili.fota.dao.mapper;

import com.ruili.fota.dao.po.FotaUsers;

public interface FotaUsersMapper {
    int insert(FotaUsers record);

    int insertSelective(FotaUsers record);
}