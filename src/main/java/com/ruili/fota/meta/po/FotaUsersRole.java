package com.ruili.fota.meta.po;

import java.util.Date;

public class FotaUsersRole {
    private Integer gid;

    private Integer adminId;

    private Integer roleId;

    private Date gmtcreate;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(Date gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public FotaUsersRole(Integer gid, Integer adminId, Integer roleId, Date gmtcreate) {
        this.gid = gid;
        this.adminId = adminId;
        this.roleId = roleId;
        this.gmtcreate = gmtcreate;
    }

    public FotaUsersRole() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
                .append(gid);
        sb.append(",\"adminId\":")
                .append(adminId);
        sb.append(",\"roleId\":")
                .append(roleId);
        sb.append(",\"gmtcreate\":\"")
                .append(gmtcreate).append('\"');
        sb.append('}');
        return sb.toString();
    }
}