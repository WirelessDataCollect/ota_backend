package com.ruili.fota.service.impl;

import com.ruili.fota.meta.po.FotaRole;
import com.ruili.fota.meta.vo.MenuVO;
import com.ruili.fota.meta.vo.RoleMenuVO;
import com.ruili.fota.meta.vo.UserRoleVO;
import com.ruili.fota.service.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Override
    public List<Map> getMenuByUser(Integer type, Integer userId) {
        return null;
    }

    @Override
    public List<FotaRole> getRole() {
        return null;
    }

    @Override
    public List<FotaRole> getRoleByUser(String username) {
        return null;
    }

    @Override
    public int addRole(FotaRole role) {
        return 0;
    }

    @Override
    public int deleteRole(Integer roleId) {
        return 0;
    }

    @Override
    public List<UserRoleVO> getUserRole() {
        return null;
    }

    @Override
    public int updateUserRole(Integer userId, List<Integer> roleIds) {
        return 0;
    }

    @Override
    public List<MenuVO> getMenu(Integer type, Integer roleId) {
        return null;
    }

    @Override
    public List<RoleMenuVO> getRoleMenu(Integer type) {
        return null;
    }

    @Override
    public int updateRoleMenu(Integer type, Integer roleId, List<Integer> menuIds) {
        return 0;
    }
}
