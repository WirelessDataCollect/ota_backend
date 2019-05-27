package com.ruili.fota.service.impl;

import com.ruili.fota.mapper.FotaUsersMapper;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private FotaUsersMapper fotaUsersMapper;

    @Override
    public FotaUsers findUserByUsername(String username) {
        Example example = new Example(FotaUsers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return fotaUsersMapper.selectOneByExample(example);
    }

    @Override
    public FotaUsers findUserByPhone(String phone) {
        Example example = new Example(FotaUsers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone", phone);
        return fotaUsersMapper.selectOneByExample(example);
    }

    @Override
    public FotaUsers findUserByEmail(String email) {
        Example example = new Example(FotaUsers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email);
        return fotaUsersMapper.selectOneByExample(example);
    }

    @Override
    public FotaUsers findUserByOpenId(String openid) {
        Example example = new Example(FotaUsers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openid", openid);
        return fotaUsersMapper.selectOneByExample(example);
    }
}
