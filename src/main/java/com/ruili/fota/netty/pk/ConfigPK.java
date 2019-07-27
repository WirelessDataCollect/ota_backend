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
public class ConfigPK implements Serializable {
    private static final long serialVersionUID = 6970659371839542595L;
    /**
     * 声明包类型
     */
    private final String type = CommandType.CONFIG.getType();
    /**
     * 接收ID
     */
    private int RecID;
    /**
     * 发送ID
     */
    private int SendID;
    /**
     * imei号信息
     */
    private String imei;
    /**
     * can端口号
     */
    private int cannum;
    /**
     * 下载方法，目前支持离线和在线两种，离线23，在线66
     */
    private int measure;
    /**
     * 总包数
     */
    private int packSize;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
            .append(type).append('\"');
        sb.append(",\"RecID\":\"")
            .append(RecID).append('\"');
        sb.append(",\"SendID\":\"")
            .append(SendID).append('\"');
        sb.append(",\"imei\":\"")
            .append(imei).append('\"');
        sb.append(",\"cannum\":\"")
            .append(cannum).append('\"');
        sb.append(",\"measure\":\"")
            .append(measure).append('\"');
        sb.append(",\"packSize\":")
            .append(packSize);
        sb.append('}');
        return sb.toString();
    }
}
