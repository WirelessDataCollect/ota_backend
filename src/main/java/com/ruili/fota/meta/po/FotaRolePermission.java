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
public class FotaRolePermission implements Serializable {

    private static final long serialVersionUID = 747665693486961857L;

    private Integer roleId;

    private Integer permissionId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtcreate;

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