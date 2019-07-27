package com.ruili.fota.netty.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 平台下发给设备的配置信息包
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DownloadOkPK implements Serializable {
    private static final long serialVersionUID = -2650034670842359359L;

    /**
     * 申明包类型
     */
    private final String type = CommandType.UPDATE_OK.getType();
    /**
     * 设备imei
     */
    private String imei;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
            .append(type).append('\"');
        sb.append(",\"imei\":\"")
            .append(imei).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
