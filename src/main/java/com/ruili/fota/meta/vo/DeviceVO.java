package com.ruili.fota.meta.vo;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.constant.OnlineStatusEnum;
import com.ruili.fota.meta.po.FotaLoaders;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

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
    @ApiModelProperty(value = "设备上线时间")
    private Date onlineTime;

    public DeviceVO(Integer id, String imei, String imsi, String deviceStatus, Date onlineTime) {
        this.id = id;
        this.imei = imei;
        this.imsi = imsi;
        this.deviceStatus = deviceStatus;
        this.onlineTime = onlineTime;
    }

    public DeviceVO(FotaLoaders loaders) {
        this.id = loaders.getGid();
        this.imei = loaders.getImei();
        this.imsi = loaders.getImsi();
        this.deviceStatus = (loaders.getOnlineStatus() == OnlineStatusEnum.ONLINE_STATUS.getCode() ? "在线" : "离线");
        this.onlineTime = loaders.getGmtupdate();
    }

    public DeviceVO() {
    }

    public Integer getId() {
        return id;
    }

    public String getImei() {
        return imei;
    }

    public String getImsi() {
        return imsi;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public Date getOnlineTime() {
        return onlineTime;
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
        sb.append('}');
        return sb.toString();
    }
}
