package com.ruili.fota.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "authority")
public class AccountController extends BaseController {

    private static String urlPrefix = "user";

    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthorityService authorityService;

    @ApiOperation(value = "账户 查询", tags = {"账户管理"}, notes = "账户 查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token", required = true),
        @ApiImplicitParam(name = "username", value = "非必选，不加此字段默认返回所有")
    })
    @PostMapping(value = "/user/get")
    public BaseResp getUser() {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

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
    @PostMapping(value = "/user/add")
    public BaseResp addUser(FotaUsers user, @RequestParam("roleIds") List<Integer> roleIds) {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        //函数中先检查是否存在，再插入新纪录
        int addRes = accountService.addUser(user);
        if (addRes == -1) {
            return new BaseResp(ResultStatus.error_duplicated_data);
        }
        if (!roleIds.isEmpty()) {
            FotaUsers theUser = accountService.findUserByUsername(user.getUsername());
            authorityService.insertOrUpdateUserRole(theUser.getGid(), roleIds);
            return new BaseResp(ResultStatus.SUCCESS);
        }
        return new BaseResp(ResultStatus.FAIL);
    }

    @ApiOperation(value = "账户 修改", tags = {"账户管理"}, notes = "账户 修改")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token"),
        @ApiImplicitParam(name = "gid", value = "用户gid", required = true),
        @ApiImplicitParam(name = "password", value = "密码，可缺省"),
        @ApiImplicitParam(name = "realname", value = "姓名，可缺省"),
        @ApiImplicitParam(name = "phone", value = "电话号码，可缺省"),
        @ApiImplicitParam(name = "email", value = "邮箱账号，可缺省"),
        @ApiImplicitParam(name = "info", value = "备注，可缺省"),
        @ApiImplicitParam(name = "status", value = "状态 0禁用 1启用，，可缺省")
    })
    @PostMapping(value = "/user/update")
    public BaseResp updateUser(FotaUsers user) {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        return new BaseResp(ResultStatus.SUCCESS, accountService.updateUser(user));
    }

    @ApiOperation(value = "账户 删除", tags = {"账户管理"}, notes = "账户 删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token", required = true),
        @ApiImplicitParam(name = "userId", value = "用户的id，返回用户的gid字段", required = true)
    })
    @PostMapping(value = "/user/delete")
    public BaseResp deleteUser(int userId) {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }
        return new BaseResp(ResultStatus.SUCCESS, accountService.deleteUserById(userId));
    }

    @ApiOperation(value = "用户-角色分配关系 查询", tags = {"账户管理-权限管理"}, notes = "用户-角色分配关系 查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token")

    })
    @PostMapping(value = "/userRole/get")
    public BaseResp getUserRole() {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        return new BaseResp(ResultStatus.SUCCESS, authorityService.getUserRole());
    }

    @ApiOperation(value = "用户-角色分配关系 修改", tags = {"账户管理-权限管理"}, notes = "用户-角色分配关系 修改")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token"),
        @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
        @ApiImplicitParam(name = "roleIds", value = "角色Id", required = true)

    })
    @PostMapping(value = "/userRole/update")
    public BaseResp updateUserRole(Integer userId, @RequestParam List<Integer> roleIds) {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        return new BaseResp(ResultStatus.SUCCESS, authorityService.insertOrUpdateUserRole(userId, roleIds));
    }

    @ApiOperation(value = "角色 查询", tags = {"账户管理-权限管理"}, notes = "角色 查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token")
    })
    @PostMapping(value = "/role/get")
    public BaseResp getRole() {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        return new BaseResp(ResultStatus.SUCCESS, authorityService.getRole());
    }

    @ApiOperation(value = "角色 添加", tags = {"账户管理-权限管理"}, notes = "角色 添加")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token"),
        @ApiImplicitParam(name = "name", value = "角色中文 用于展示", required = true),
        @ApiImplicitParam(name = "value", value = "角色英文", required = true),
        @ApiImplicitParam(name = "info", value = "角色描述 非必须"),
    })
    @PostMapping(value = "/role/add")
    public BaseResp addRole(FotaRole role) {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

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
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        return new BaseResp(ResultStatus.SUCCESS, authorityService.deleteRole(roleId));
    }

    @ApiOperation(value = "权限 查询", tags = {"账户管理-权限管理"}, notes = "权限 查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token")
    })
    @PostMapping(value = "/menu/get")
    public BaseResp getMenu() {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        //TODO 根据用户的请求身份获取用户信息再请求菜单接口
        return new BaseResp(ResultStatus.SUCCESS, authorityService.getMenu(AuthorityEnum.MENU_TYPE_PC.getType(), null));
    }

    @ApiOperation(value = "角色-权限分配关系 添加", tags = {"账户管理-权限管理"}, notes = "角色-权限分配关系 添加")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token"),
        @ApiImplicitParam(name = "roleId", value = "角色Id", required = true),
        @ApiImplicitParam(name = "menuIds", value = "菜单们Id", required = true)
    })
    @PostMapping(value = "/roleMenu/update")
    public BaseResp updateRoleMenu(Integer roleId, @RequestParam List<Integer> menuIds) {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        authorityService.updateRoleMenu(AuthorityEnum.MENU_TYPE_PC.getType(), roleId, menuIds);
        return new BaseResp(ResultStatus.SUCCESS);
    }

    @ApiOperation(value = "角色-权限分配关系 查询", tags = {"账户管理-权限管理"}, notes = "角色-权限分配关系 查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token")
    })
    @PostMapping(value = "/roleMenu/get")
    public BaseResp getRoleMenu() {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }
        return new BaseResp(ResultStatus.SUCCESS, authorityService.getRoleMenu(AuthorityEnum.MENU_TYPE_PC.getType()));
    }

}
