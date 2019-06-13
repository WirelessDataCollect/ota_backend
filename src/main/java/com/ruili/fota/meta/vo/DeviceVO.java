package com.ruili.fota.meta.vo;

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
    @ApiModelProperty(value = "设备最后在线的时刻，通过心跳包判断")
    private Date onlineTime;
    @ApiModelProperty(value = "设备最后升级的时刻，通过清除requestId判断")
    private Date downloadTime;
    @ApiModelProperty(value = "设备固件升级请求id，requestId，未升级时为null，升级时是String")
    private String requestId;

    public DeviceVO(Integer id, String imei, String imsi, String deviceStatus, Date onlineTime, Date downloadTime, String requestId) {
        this.id = id;
        this.imei = imei;
        this.imsi = imsi;
        this.deviceStatus = deviceStatus;
        this.onlineTime = onlineTime;
        this.downloadTime = downloadTime;
        this.requestId = requestId;
    }

    public DeviceVO(FotaLoaders loaders) {
        this.id = loaders.getGid();
        this.imei = loaders.getImei();
        this.imsi = loaders.getImsi();
        this.deviceStatus = (loaders.getOnlineStatus() == OnlineStatusEnum.ONLINE_STATUS.getCode() ? "在线" : "离线");
        this.onlineTime = loaders.getGmtupdate();
        this.downloadTime = loaders.getGmtmodified();
        this.requestId = loaders.getRequestId();
    }

    public DeviceVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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
