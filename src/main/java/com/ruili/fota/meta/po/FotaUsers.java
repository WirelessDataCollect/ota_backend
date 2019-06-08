package com.ruili.fota.meta.po;

import java.util.Date;

public class FotaUsers {
    private Integer gid;

    private String username;

    private String phone;

    private String openid;

    private String password;

    private String realname;

    private String email;

    private Integer status;

    private String info;

    private String lastloginip;

    private Date gmtcreate;

    private Date gmtupdate;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip == null ? null : lastloginip.trim();
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

    public FotaUsers(Integer gid, String username, String phone, String openid, String password, String realname, String email, Integer status, String info, String lastloginip, Date gmtcreate, Date gmtupdate) {
        this.gid = gid;
        this.username = username;
        this.phone = phone;
        this.openid = openid;
        this.password = password;
        this.realname = realname;
        this.email = email;
        this.status = status;
        this.info = info;
        this.lastloginip = lastloginip;
        this.gmtcreate = gmtcreate;
        this.gmtupdate = gmtupdate;
    }

    public FotaUsers() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
                .append(gid);
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"openid\":\"")
                .append(openid).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"realname\":\"")
                .append(realname).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"info\":\"")
                .append(info).append('\"');
        sb.append(",\"lastloginip\":\"")
                .append(lastloginip).append('\"');
        sb.append(",\"gmtcreate\":\"")
                .append(gmtcreate).append('\"');
        sb.append(",\"gmtupdate\":\"")
                .append(gmtupdate).append('\"');
        sb.append('}');
        return sb.toString();
    }
}