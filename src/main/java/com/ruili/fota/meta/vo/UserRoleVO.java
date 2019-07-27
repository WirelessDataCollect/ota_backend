package com.ruili.fota.meta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleVO implements Serializable {

    private static final long serialVersionUID = 8520469882771571457L;

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
