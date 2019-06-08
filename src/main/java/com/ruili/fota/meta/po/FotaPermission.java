package com.ruili.fota.meta.po;

import java.util.Date;

public class FotaPermission {
    private Integer gid;

    private String code;

    private String pCode;

    private Integer pId;

    private String name;

    private String url;

    private Integer isMenu;

    private Integer level;

    private Integer sort;

    private Integer status;

    private Date gmtcreate;

    private Date gmtupdate;

    private Integer type;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode == null ? null : pCode.trim();
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public FotaPermission(Integer gid, String code, String pCode, Integer pId, String name, String url, Integer isMenu, Integer level, Integer sort, Integer status, Date gmtcreate, Date gmtupdate, Integer type) {
        this.gid = gid;
        this.code = code;
        this.pCode = pCode;
        this.pId = pId;
        this.name = name;
        this.url = url;
        this.isMenu = isMenu;
        this.level = level;
        this.sort = sort;
        this.status = status;
        this.gmtcreate = gmtcreate;
        this.gmtupdate = gmtupdate;
        this.type = type;
    }

    public FotaPermission() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
                .append(gid);
        sb.append(",\"code\":\"")
                .append(code).append('\"');
        sb.append(",\"pCode\":\"")
                .append(pCode).append('\"');
        sb.append(",\"pId\":")
                .append(pId);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"isMenu\":")
                .append(isMenu);
        sb.append(",\"level\":")
                .append(level);
        sb.append(",\"sort\":")
                .append(sort);
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"gmtcreate\":\"")
                .append(gmtcreate).append('\"');
        sb.append(",\"gmtupdate\":\"")
                .append(gmtupdate).append('\"');
        sb.append(",\"type\":")
                .append(type);
        sb.append('}');
        return sb.toString();
    }
}