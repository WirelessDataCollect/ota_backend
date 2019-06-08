package com.ruili.fota.meta.po;

import java.util.Date;

public class FotaRole {
    private Integer gid;

    private String name;

    private String value;

    private String info;

    private Integer status;

    private Date gmtcreate;

    private Date gmtupdate;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(Date gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public Date getGmtupdate() {
        return gmtupdate;
    }

    public void setGmtupdate(Date gmtupdate) {
        this.gmtupdate = gmtupdate;
    }

    public FotaRole(Integer gid, String name, String value, String info, Integer status, Date gmtcreate, Date gmtupdate) {
        this.gid = gid;
        this.name = name;
        this.value = value;
        this.info = info;
        this.status = status;
        this.gmtcreate = gmtcreate;
        this.gmtupdate = gmtupdate;
    }

    public FotaRole() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
                .append(gid);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"value\":\"")
                .append(value).append('\"');
        sb.append(",\"info\":\"")
                .append(info).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"gmtcreate\":\"")
                .append(gmtcreate).append('\"');
        sb.append(",\"gmtupdate\":\"")
                .append(gmtupdate).append('\"');
        sb.append('}');
        return sb.toString();
    }
}