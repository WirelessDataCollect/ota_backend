package com.ruili.fota.service;

import com.ruili.fota.common.RequestHelper;
import com.ruili.fota.meta.po.FotaUsers;

import java.util.List;

public interface AccountService {

    /**
     * 通过userName查询
     *
     * @param username
     * @return
     */
    public FotaUsers findUserByUsername(String username);

    /**
     * 通过电话查询
     *
     * @param phone
     * @return
     */
    public FotaUsers findUserByPhone(String phone);

    /**
     * 通过email查询
     *
     * @param email
     * @return
     */
    public FotaUsers findUserByEmail(String email);

    /**
     * 通过openid查询
     *
     * @param openid
     * @return
     */
    public FotaUsers findUserByOpenId(String openid);

    /**
     * 查询用户信息
     *
     * @param requestHelper
     * @return
     */
    public List<FotaUsers> getUser(RequestHelper requestHelper);

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    public int addUser(FotaUsers user);

    /**
     * 修改 用户
     *
     * @param user
     * @return
     */
    int updateUser(FotaUsers user);

    /**
     * 通过id删除用户
     * @param userId
     * @return
     */
    int deleteUserById(int userId);

}
