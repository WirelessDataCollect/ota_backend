package com.ruili.fota.mapper;

import com.ruili.fota.common.BaseMapper;
import com.ruili.fota.meta.po.FotaRole;
import com.ruili.fota.meta.vo.MenuVO;
import com.ruili.fota.meta.vo.RoleMenuVO;
import com.ruili.fota.meta.vo.UserRoleVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface FotaRoleMapper extends BaseMapper<FotaRole> {

    /**
     * 获取 登陆用户可查看的菜单
     *
     * @param type
     * @param userId
     * @return
     */
    @Select("SELECT menu.id,menu.name,menu.code FROM fota_users_role u_r LEFT JOIN fota_role_permission r_m ON u_r.role_Id=r_m.role_id RIGHT JOIN fota_permission menu ON r_m.permission_id=menu.id AND menu.type=#{type} WHERE user_id=#{userId}")
    List<Map> selectMenuByUser(@Param("type") Integer type, @Param("userId") Integer userId);

    /**
     * 查找 角色
     *
     * @param username
     * @return
     */
    List<FotaRole> selectRoleByUser(@Param("username") String username);

    /**
     * 查找 user-role 对应关系
     *
     * @return
     */
    List<UserRoleVO> selectUserRole();

    /**
     * 删除 user-role 对应关系
     *
     * @param userId
     * @return
     */
    @Delete("DELETE FROM fota_users_role WHERE user_id=#{userId}")
    int deleteUserRole(@Param("userId") Integer userId);

    /**
     * 删除 user-role 对应关系 通过角色
     *
     * @param roleId
     * @return
     */
    @Delete("DELETE FROM fota_users_role WHERE role_id=#{roleId}")
    int deleteUserRoleByRoleId(@Param("roleId") Integer roleId);

    /**
     * 添加 user-role 对应关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    int insertUserRole(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    /**
     * 查询 menu
     *
     * @param type
     * @return
     */
    List<MenuVO> selectMenu(@Param("type") Integer type);

    /**
     * 根据角色获取菜单
     *
     * @param type
     * @param roleId
     * @return
     */
    @Select("SELECT id 'key',url title FROM fota_permission menu INNER JOIN fota_role_permission r_m ON r_m.permission_id=menu.id AND type=#{type} WHERE role_id=#{roleId}")
    List<MenuVO> selectMenuByRole(@Param("type") Integer type, @Param("roleId") Integer roleId);

    /**
     * 查询 role-menu 对应关系
     *
     * @param type
     * @return
     */
    List<RoleMenuVO> selectRoleMenu(@Param("type") Integer type);

    /**
     * 修改 role-menu 对应关系
     *
     * @param type
     * @param roleId
     * @return
     */
    @Delete("DELETE FROM fota_role_permission WHERE role_id=#{roleId} AND permission_id IN(SELECT permission_id FROM (SELECT permission_id FROM fota_role_permission r_m RIGHT JOIN fota_permission menu ON r_m.permission_id=menu.id AND menu.type=#{type} WHERE role_id=#{roleId}) menu_id) ")
    int deleteRoleMenu(@Param("type") Integer type, @Param("roleId") Integer roleId);

    /**
     * 剔除ismenu=1的
     *
     * @param menuIds
     * @return
     */
    @Select("SELECT id FROM fota_permission WHERE id IN(${menuIds}) AND is_menu=0")
    List<Integer> selectMenuByIsMenu(@Param("menuIds") String menuIds);

    /**
     * 添加 role-menu 对应关系
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    int insertRoleMenu(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

}