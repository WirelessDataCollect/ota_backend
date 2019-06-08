package com.ruili.fota.meta.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yin on 2019/3/8
 * 用户 和 角色 的分配关系
 */
public class UserRoleVO {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 账号 用户名
     */
    private String username;
    /**
     * 用户真实姓名
     */
    private String realname;
    /**
     * 用户的状态
     */
    private Integer status;
    /**
     * 角色ID们
     */
    private List<Integer> rids;
    /**
     * 角色的名称们 ',' 拼接 例子: 角色1,角色2
     */
    private String roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getRids() {
        return rids;
    }

    public void setRids(String rids) {
        this.rids = new ArrayList<>();

        String[] ridsStr = rids.split(",");

        for (String ridStr : ridsStr) {
            this.rids.add(Integer.parseInt(ridStr));
        }
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public UserRoleVO(Integer id, String username, String realname, Integer status, List<Integer> rids, String roles) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.status = status;
        this.rids = rids;
        this.roles = roles;
    }

    public UserRoleVO() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"realname\":\"")
                .append(realname).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"rids\":")
                .append(rids);
        sb.append(",\"roles\":\"")
                .append(roles).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
