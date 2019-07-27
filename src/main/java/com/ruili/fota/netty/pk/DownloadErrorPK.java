package com.ruili.fota.netty.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DownloadErrorPK implements Serializable {

    private static final long serialVersionUID = -6833975663927557679L;
    /**
     * 申明包类型
     */
    private final String type = CommandType.UPDATE_ERROR.getType();
    /**
     * 设备imei
     */
    private String imei;
    /**
     * 下载过程的状态码
     */
    private int code;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
            .append(type).append('\"');
        sb.append(",\"imei\":\"")
            .append(imei).append('\"');
        sb.append(",\"code\":")
            .append(code);
        sb.append('}');
        return sb.toString();
    }
}
