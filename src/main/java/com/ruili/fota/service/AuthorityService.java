package com.ruili.fota.service;

import com.ruili.fota.meta.po.FotaRole;
import com.ruili.fota.meta.vo.MenuVO;
import com.ruili.fota.meta.vo.RoleMenuVO;
import com.ruili.fota.meta.vo.UserRoleVO;

import java.util.List;
import java.util.Map;

public interface AuthorityService {
    /**
     * 获取 登陆用户可查看的菜单
     *
     * @param type
     * @param userId
     * @return
     */
    List<Map> getMenuByUser(Integer type, Integer userId);

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
    List<FotaRole> getRoleByUser(String username);

    /**
     * 添加 角色
     *
     * @param role
     * @return
     */
    int addRole(FotaRole role);

    /**
     * 删除 角色
     *
     * @param roleId
     * @return
     */
    int deleteRole(Integer roleId);

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
    int insertOrUpdateUserRole(Integer userId, List<Integer> roleIds);

    /**
     * 获取所有菜单 或者根据角色获取菜单
     *
     * @param type   0微信 1PC
     * @param roleId
     * @return
     */
    List<MenuVO> getMenu(Integer type, Integer roleId);

    /**
     * 查询 角色-菜单 对应关系
     *
     * @param type 0微信 1PC
     * @return
     */
    List<RoleMenuVO> getRoleMenu(Integer type);

    /**
     * 修改 角色-菜单 对应关系
     *
     * @param roleId
     * @param menuIds
     * @param type    0微信 1PC
     * @return
     */
    int updateRoleMenu(Integer type, Integer roleId, List<Integer> menuIds);


}
