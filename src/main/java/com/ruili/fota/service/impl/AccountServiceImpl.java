package com.ruili.fota.service.impl;

import com.ruili.fota.common.RequestHelper;
import com.ruili.fota.mapper.FotaUsersMapper;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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

    @Override
    public List<FotaUsers> getUser(RequestHelper requestHelper) {
        String username = requestHelper.getString("username");
        if (username == null && username == "") {
            return fotaUsersMapper.selectAll();
        }
        Example example = new Example(FotaUsers.class);
        example.createCriteria().andLike("username", username);
        return fotaUsersMapper.selectByExample(example);
    }

    @Override
    public int addUser(FotaUsers user) {
        FotaUsers theUser = findUserByUsername(user.getUsername());
        if (theUser != null) {
            return -1;
        }
        return fotaUsersMapper.insertSelective(user);
    }

    @Override
    public int updateUser(FotaUsers user) {
        if (!StringUtils.isEmpty(user.getStatus())) {
            Integer status = fotaUsersMapper.selectByPrimaryKey(user.getGid()).getStatus();
            status = (status == 1 ? 0 : 1);
            user.setStatus(status);
        }
        return fotaUsersMapper.updateByPrimaryKeySelective(user);
    }
}
