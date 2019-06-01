package com.ruili.fota.meta.vo;

import com.alibaba.fastjson.JSON;
import com.ruili.fota.meta.bo.ConfigBO;
import com.ruili.fota.meta.po.FotaLoadHistory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "OTA历史记录")
public class OtaHistoryVO {
    @ApiModelProperty(value = "设备id")
    private Integer id;
    @ApiModelProperty(value = "设备imei")
    private String imei;
    @ApiModelProperty(value = "设备状态")
    private String deviceStatus;
    @ApiModelProperty(value = "固件的配置信息")
    private ConfigBO configBO;
    @ApiModelProperty(value = "固件升级时间")
    private Date upgradeTime;


    public OtaHistoryVO(FotaLoadHistory history) {
        this.id = history.getGid();
        this.imei = history.getImei();
        this.deviceStatus = history.getLoadProcess();
        this.configBO = JSON.parseObject(history.getConfigBo(), ConfigBO.class);
        this.upgradeTime = history.getGmtcreate();
    }

    public OtaHistoryVO() {
    }

    public OtaHistoryVO(Integer id, String imei, String deviceStatus, ConfigBO configBO, Date upgradeTime) {
        this.id = id;
        this.imei = imei;
        this.deviceStatus = deviceStatus;
        this.configBO = configBO;
        this.upgradeTime = upgradeTime;
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

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public ConfigBO getConfigBO() {
        return configBO;
    }

    public void setConfigBO(ConfigBO configBO) {
        this.configBO = configBO;
    }

    public Date getUpgradeTime() {
        return upgradeTime;
    }

    public void setUpgradeTime(Date upgradeTime) {
        this.upgradeTime = upgradeTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"deviceStatus\":\"")
                .append(deviceStatus).append('\"');
        sb.append(",\"configBO\":\"")
                .append(configBO).append('\"');
        sb.append(",\"upgradeTime\":\"")
                .append(upgradeTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
