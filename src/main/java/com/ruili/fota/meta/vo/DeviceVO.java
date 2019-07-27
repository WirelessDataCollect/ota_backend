package com.ruili.fota.meta.vo;

import com.ruili.fota.constant.OnlineStatusEnum;
import com.ruili.fota.meta.po.FotaLoaders;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "设备列表")
public class DeviceVO {

    @ApiModelProperty(value = "设备id")
    private Integer id;
    @ApiModelProperty(value = "imei号")
    private String imei;
    @ApiModelProperty(value = "imsi号")
    private String imsi;
    @ApiModelProperty(value = "设备状态")
    private String deviceStatus;
    @ApiModelProperty(value = "设备最后在线的时刻，通过心跳包判断")
    private Date onlineTime;
    @ApiModelProperty(value = "设备最后升级的时刻，通过清除requestId判断")
    private Date downloadTime;
    @ApiModelProperty(value = "设备固件升级请求id，requestId，未升级时为null，升级时是String")
    private String requestId;

    public DeviceVO(FotaLoaders loaders) {
        this.id = loaders.getGid();
        this.imei = loaders.getImei();
        this.imsi = loaders.getImsi();
        this.deviceStatus = (loaders.getOnlineStatus() == OnlineStatusEnum.ONLINE_STATUS.getCode() ? "在线" : "离线");
        this.onlineTime = loaders.getGmtupdate();
        this.downloadTime = loaders.getGmtmodified();
        this.requestId = loaders.getRequestId();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"imsi\":\"")
                .append(imsi).append('\"');
        sb.append(",\"deviceStatus\":\"")
                .append(deviceStatus).append('\"');
        sb.append(",\"onlineTime\":\"")
                .append(onlineTime).append('\"');
        sb.append(",\"downloadTime\":\"")
                .append(downloadTime).append('\"');
        sb.append(",\"requestId\":\"")
                .append(requestId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
