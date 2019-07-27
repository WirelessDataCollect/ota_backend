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
public class FotaPermission implements Serializable {

    private static final long serialVersionUID = -6852790274387671806L;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtcreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtupdate;

    private Integer type;

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