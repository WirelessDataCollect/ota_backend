package com.ruili.fota.meta.po;

import java.util.Date;

public class FotaUsersRole {
    private Integer gid;

    private Integer adminId;

    private Integer roleId;

    private Date gmtcreate;

    private String schoolids;

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

    public String getSchoolids() {
        return schoolids;
    }

    public void setSchoolids(String schoolids) {
        this.schoolids = schoolids == null ? null : schoolids.trim();
    }
}