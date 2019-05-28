package com.ruili.fota.controller;

import com.ruili.fota.constant.AuthorityEnum;
import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import com.ruili.fota.meta.po.FotaRole;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.service.AccountService;
import com.ruili.fota.service.AuthorityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthorityService authorityService;

    @ApiOperation(value = "账户 查询", tags = {"账户管理"}, notes = "账户 查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token", required = true),
            @ApiImplicitParam(name = "username", value = "非必选，usernam的模糊查询内容")
    })
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public BaseResp getUser() {
        return new BaseResp(ResultStatus.SUCCESS, accountService.getUser(this.getRequestHelper()));
    }

    @ApiOperation(value = "账户 添加", tags = {"账户管理"}, notes = "账户 添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "realname", value = "姓名", required = true),
            @ApiImplicitParam(name = "phone", value = "电话号码", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱账号", required = true),
            @ApiImplicitParam(name = "info", value = "备注"),
            @ApiImplicitParam(name = "roleIds", value = "角色ID", required = true),
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResp addUser(FotaUsers user, @RequestParam("roleIds") List<Integer> roleIds) {
        int addRes = accountService.addUser(user);
        if (addRes == -1) {
            return new BaseResp(ResultStatus.error_duplicated_data);
        }
        if (user.getGid() != null) {
            authorityService.updateUserRole(user.getGid(), roleIds);
            return new BaseResp(ResultStatus.SUCCESS);
        }
        return new BaseResp(ResultStatus.FAIL);
    }

    @ApiOperation(value = "账户 修改", tags = {"账户管理"}, notes = "账户 修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token"),
            @ApiImplicitParam(name = "id", value = "用户id", required = true),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "realname", value = "姓名"),
            @ApiImplicitParam(name = "phone", value = "电话号码"),
            @ApiImplicitParam(name = "email", value = "邮箱账号"),
            @ApiImplicitParam(name = "info", value = "备注"),
            @ApiImplicitParam(name = "status", value = "状态 0禁用 1启用")
    })
    @RequestMapping(value = "/account-mgt/account/update", method = RequestMethod.POST)
    public BaseResp updateUser(FotaUsers user) {
        return new BaseResp(ResultStatus.SUCCESS, accountService.updateUser(user));
    }

    @ApiOperation(value = "用户-角色分配关系 查询", tags = {"账户管理-权限管理"}, notes = "用户-角色分配关系 查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token")

    })
    @RequestMapping(value = "/account-mgt/authority/user-role/get", method = RequestMethod.GET)
    public BaseResp getUserRole() {

        return new BaseResp(ResultStatus.SUCCESS, authorityService.getUserRole());
    }

    @ApiOperation(value = "用户-角色分配关系 修改", tags = {"账户管理-权限管理"}, notes = "用户-角色分配关系 修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token"),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "roleIds", value = "角色Id", required = true)

    })
    @RequestMapping(value = "/account-mgt/authority/user-role/update", method = RequestMethod.POST)
    public BaseResp updateUserRole(Integer userId, @RequestParam List<Integer> roleIds) {
        return new BaseResp(ResultStatus.SUCCESS, authorityService.updateUserRole(userId, roleIds));
    }

    @ApiOperation(value = "角色 查询", tags = {"账户管理-权限管理"}, notes = "角色 查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token")
    })
    @PostMapping(value = "/role/get")
    public BaseResp getRole() {
        return new BaseResp(ResultStatus.SUCCESS, authorityService.getRole());
    }

    @ApiOperation(value = "角色 添加", tags = {"账户管理-权限管理"}, notes = "角色 添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token"),
            @ApiImplicitParam(name = "name", value = "角色中文 用于展示", required = true),
            @ApiImplicitParam(name = "value", value = "角色英文", required = true),
            @ApiImplicitParam(name = "description", value = "角色描述 非必须"),
    })
    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
    public BaseResp addRole(FotaRole role) {
        int addRes = authorityService.addRole(role);

        if (addRes == -1) {
            return new BaseResp(ResultStatus.error_duplicated_data);
        }
        return new BaseResp(ResultStatus.SUCCESS);
    }

    @ApiOperation(value = "角色 删除", tags = {"账户管理-权限管理"}, notes = "角色 删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token"),
            @ApiImplicitParam(name = "roleId", value = "角色ID")
    })
    @PostMapping(value = "/role/delete")
    public BaseResp deleteRole(Integer roleId) {
        return new BaseResp(ResultStatus.SUCCESS, authorityService.deleteRole(roleId));
    }

    @ApiOperation(value = "权限 查询", tags = {"账户管理-权限管理"}, notes = "权限 查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token")
    })
    @PostMapping(value = "/menu/get")
    public BaseResp getMenu() {
        return new BaseResp(ResultStatus.SUCCESS, authorityService.getMenu(AuthorityEnum.MENU_TYPE_PC.getType(), null));
    }

    @ApiOperation(value = "角色-权限分配关系 添加", tags = {"账户管理-权限管理"}, notes = "角色-权限分配关系 添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token"),
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = true),
            @ApiImplicitParam(name = "menuIds", value = "菜单们Id", required = true)
    })
    @PostMapping(value = "/role-menu/update")
    public BaseResp updateRoleMenu(Integer roleId, @RequestParam List<Integer> menuIds) {
        authorityService.updateRoleMenu(AuthorityEnum.MENU_TYPE_PC.getType(), roleId, menuIds);
        return new BaseResp(ResultStatus.SUCCESS);
    }

    @ApiOperation(value = "角色-权限分配关系 查询", tags = {"账户管理-权限管理"}, notes = "角色-权限分配关系 查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token")
    })
    @PostMapping(value = "/role-menu/get")
    public BaseResp getRoleMenu() {
        return new BaseResp(ResultStatus.SUCCESS, authorityService.getRoleMenu(AuthorityEnum.MENU_TYPE_PC.getType()));
    }

}
