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
public class HeartBeatPK_ACK implements Serializable {
    private static final long serialVersionUID = -8860411168778297044L;
    /**
     * 申明包类型
     */
    private final String type = CommandType.HEARTBEAT_ACK.getType();
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
