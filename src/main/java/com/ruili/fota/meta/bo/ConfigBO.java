package com.ruili.fota.meta.bo;


import com.ruili.fota.meta.po.FotaImages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigBO implements Serializable {
    private static final long serialVersionUID = 9217119683855875609L;
    /**
     * 接收id
     */
    private String RecID;
    /**
     * 发送id
     */
    private String SendID;
    /**
     * 升级设备的imei
     */
    private String imei;
    /**
     * 配置的can接口
     */
    private int cannum;
    /**
     * 升级方法，离线或在线，在数据库中进行记录
     */
    private int measure;
    /**
     * 升级的固件id
     */
    private String firmwareId;
    /**
     * mcu类型
     */
    private String mcuType;

    /**
     * 下发配置过来，主动去数据库查询
     */
    private FotaImages fotaImages;

    /**
     * 租户id
     */
    private String tenantId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"RecID\":\"")
            .append(RecID).append('\"');
        sb.append(",\"SendID\":\"")
            .append(SendID).append('\"');
        sb.append(",\"imei\":\"")
            .append(imei).append('\"');
        sb.append(",\"cannum\":")
            .append(cannum);
        sb.append(",\"measure\":")
            .append(measure);
        sb.append(",\"firmwareId\":\"")
            .append(firmwareId).append('\"');
        sb.append(",\"mcuType\":\"")
            .append(mcuType).append('\"');
        sb.append(",\"fotaImages\":")
            .append(fotaImages);
        sb.append(",\"tenantId\":\"")
            .append(tenantId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
