package com.ruili.fota.service.impl;

import com.ruili.fota.constant.AuthorityEnum;
import com.ruili.fota.mapper.FotaRoleMapper;
import com.ruili.fota.meta.po.FotaRole;
import com.ruili.fota.meta.vo.MenuVO;
import com.ruili.fota.meta.vo.RoleMenuVO;
import com.ruili.fota.meta.vo.UserRoleVO;
import com.ruili.fota.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class AuthorityServiceImpl extends BaseService implements AuthorityService {

    @Autowired
    private FotaRoleMapper fotaRoleMapper;

    @Override
    public List<Map> getMenuByUser(Integer type, Integer userId) {
        return fotaRoleMapper.selectMenuByUser(type, userId);
    }

    @Override
    public List<FotaRole> getRole() {
        return fotaRoleMapper.selectAll();
    }

    @Override
    public List<FotaRole> getRoleByUser(String username) {
        return fotaRoleMapper.selectRoleByUser(username);
    }

    @Override
    public int addRole(FotaRole role) {
        role.setValue(role.getValue().toUpperCase());
        List roles = findObjectsByProperty("value", role.getValue(), fotaRoleMapper, FotaRole.class);
        if (roles != null && roles.size() != 0) {
            return -1;
        }
        return fotaRoleMapper.insertSelective(role);
    }

    @Override
    public int deleteRole(Integer roleId) {
        //先删除两个关联表
        fotaRoleMapper.deleteUserRoleByRoleId(roleId);
        fotaRoleMapper.deleteRoleMenu(AuthorityEnum.MENU_TYPE_WX.getType(), roleId);
        fotaRoleMapper.deleteRoleMenu(AuthorityEnum.MENU_TYPE_PC.getType(), roleId);
        //在删除角色
        return fotaRoleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public List<UserRoleVO> getUserRole() {
        return fotaRoleMapper.selectUserRole();
    }

    @Override
    public int updateUserRole(Integer userId, List<Integer> roleIds) {
        int res;
        //先删除原有对应关系
        res = fotaRoleMapper.deleteUserRole(userId);
        //添加对应关系
        res = res * fotaRoleMapper.insertUserRole(userId, roleIds);
        return res;
    }

    @Override
    public List<MenuVO> getMenu(Integer type, Integer roleId) {
        if (StringUtils.isEmpty(roleId)) {
            return fotaRoleMapper.selectMenu(type);
        }
        return fotaRoleMapper.selectMenuByRole(type, roleId);
    }

    @Override
    public List<RoleMenuVO> getRoleMenu(Integer type) {
        return fotaRoleMapper.selectRoleMenu(type);
    }

    @Override
    public int updateRoleMenu(Integer type, Integer roleId, List<Integer> menuIds) {
        //先删除原有对应关系 删除是类型type
        fotaRoleMapper.deleteRoleMenu(type, roleId);
        StringBuffer menuIdsStr = new StringBuffer();
        for (Integer menuId : menuIds) {
            menuIdsStr.append(",").append(menuId);
        }
        //剔除ismenu=1的
        List<Integer> isMenuIds = fotaRoleMapper.selectMenuByIsMenu(menuIdsStr.substring(1));
        return fotaRoleMapper.insertRoleMenu(roleId, isMenuIds);
    }
}
