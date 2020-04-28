package com.ruili.fota.service;

import com.ruili.fota.common.RequestHelper;
import com.ruili.fota.meta.po.FotaUsers;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface AccountService {

    /**
     * 通过userName查询
     *
     * @param username
     * @return
     */
    public FotaUsers findUserByUsername(@NotBlank String username);

    /**
     * 通过电话查询
     *
     * @param phone
     * @return
     */
    public FotaUsers findUserByPhone(@NotBlank String phone);

    /**
     * 通过email查询
     *
     * @param email
     * @return
     */
    public FotaUsers findUserByEmail(@NotBlank String email);

    /**
     * 通过openid查询
     *
     * @param openid
     * @return
     */
    public FotaUsers findUserByOpenId(@NotBlank String openid);

    /**
     * 查询用户信息
     *
     * @param requestHelper
     * @return
     */
    public List<FotaUsers> getUser(@NotBlank RequestHelper requestHelper);

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    public int addUser(@NotNull FotaUsers user);

    /**
     * 修改 用户
     *
     * @param user
     * @return
     */
    int updateUser(@NotNull FotaUsers user);

    /**
     * 通过id删除用户
     * @param userId
     * @return
     */
    int deleteUserById(@NotNull int userId);

    /**
     * 管理员用户个数
     * @return 管理员用户个数
     */
    int countManagerUser();

}
