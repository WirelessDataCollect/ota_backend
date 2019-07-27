package com.ruili.fota.meta.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FotaUsers implements Serializable {

    private static final long serialVersionUID = 8172844046736723115L;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtcreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtupdate;

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