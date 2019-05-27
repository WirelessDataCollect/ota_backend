package com.ruili.fota.service;

import com.ruili.fota.meta.po.FotaUsers;
import org.springframework.stereotype.Service;

public interface AccountService {

    public FotaUsers findUserByUsername(String username);

    public FotaUsers findUserByPhone(String phone);

    public FotaUsers findUserByEmail(String email);

    public FotaUsers findUserByOpenId(String openid);
}
