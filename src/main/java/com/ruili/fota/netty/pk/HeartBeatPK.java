package com.ruili.fota.netty.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 设备心跳包
 * @author: jingxiong.ljx
 * @date: 2019-07-27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartBeatPK implements Serializable {
    private static final long serialVersionUID = -573486452726762539L;

    /**
     * 声明包类型
     */
    private final String type = CommandType.HEARTBEAT.getType();
    /**
     * 设备imei号
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
