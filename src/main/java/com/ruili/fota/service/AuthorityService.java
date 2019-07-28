package com.ruili.fota.service;

import com.ruili.fota.meta.po.FotaRole;
import com.ruili.fota.meta.vo.MenuVO;
import com.ruili.fota.meta.vo.RoleMenuVO;
import com.ruili.fota.meta.vo.UserRoleVO;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface AuthorityService {
    /**
     * 获取 登陆用户可查看的菜单
     *
     * @param type
     * @param userId
     * @return
     */
    List<Map> getMenuByUser(@NotNull Integer type,@NotNull Integer userId);

    /**
     * 查询 角色
     *
     * @return
     */
    List<FotaRole> getRole();


    /**
     * 查询 用户拥有的角色
     *
     * @param username
     * @return
     */
    List<FotaRole> getRoleByUser(@NotBlank String username);

    /**
     * 添加 角色
     *
     * @param role
     * @return
     */
    int addRole(@NotNull FotaRole role);

    /**
     * 删除 角色
     *
     * @param roleId
     * @return
     */
    int deleteRole(@NotNull Integer roleId);

    /**
     * 查询 用户-角色 对应关系
     *
     * @return
     */
    List<UserRoleVO> getUserRole();

    /**
     * 修改 用户-角色 对应关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    int insertOrUpdateUserRole(@NotNull Integer userId,@NotBlank List<Integer> roleIds);

    /**
     * 获取所有菜单 或者根据角色获取菜单
     *
     * @param type   0微信 1PC
     * @param roleId
     * @return
     */
    List<MenuVO> getMenu(@NotNull Integer type,@NotNull Integer roleId);

    /**
     * 查询 角色-菜单 对应关系
     *
     * @param type 0微信 1PC
     * @return
     */
    List<RoleMenuVO> getRoleMenu(@NotNull Integer type);

    /**
     * 修改 角色-菜单 对应关系
     *
     * @param roleId
     * @param menuIds
     * @param type    0微信 1PC
     * @return
     */
    int updateRoleMenu(@NotNull Integer type,@NotNull Integer roleId,@NotBlank List<Integer> menuIds);


}
