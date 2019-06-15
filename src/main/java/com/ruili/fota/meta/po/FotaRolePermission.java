package com.ruili.fota.meta.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FotaRolePermission {
    private Integer roleId;

    private Integer permissionId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtcreate;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Date getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(Date gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public FotaRolePermission(Integer roleId, Integer permissionId, Date gmtcreate) {
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.gmtcreate = gmtcreate;
    }

    public FotaRolePermission() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"roleId\":")
                .append(roleId);
        sb.append(",\"permissionId\":")
                .append(permissionId);
        sb.append(",\"gmtcreate\":\"")
                .append(gmtcreate).append('\"');
        sb.append('}');
        return sb.toString();
    }
}