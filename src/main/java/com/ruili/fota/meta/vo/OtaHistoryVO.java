package com.ruili.fota.meta.vo;

import com.alibaba.fastjson.JSON;
import com.ruili.fota.constant.LoadStatusEnum;
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
    @ApiModelProperty(value = "设备升级状态，66代表升级成功，23代表升级失败")
    private Object loadStatus;
    @ApiModelProperty(value = "固件的配置信息")
    private ConfigBO configBO;
    @ApiModelProperty(value = "固件升级开始时间")
    private Date upgradeStartTime;
    @ApiModelProperty(value = "固件升级结束时间")
    private Date upgradeEndTime;


    public OtaHistoryVO(FotaLoadHistory history) {
        this.id = history.getGid();
        this.imei = history.getImei();
        this.loadStatus = JSON.parseObject(history.getLoadProcess());
        this.configBO = JSON.parseObject(history.getConfigBo(), ConfigBO.class);
        this.upgradeStartTime = history.getGmtupdate();
        this.upgradeEndTime = history.getGmtmodified();
    }

    public OtaHistoryVO() {
    }

    public OtaHistoryVO(Integer id, String imei, LoadStatusEnum loadStatus, ConfigBO configBO, Date upgradeStartTime, Date upgradeEndTime) {
        this.id = id;
        this.imei = imei;
        this.loadStatus = loadStatus;
        this.configBO = configBO;
        this.upgradeStartTime = upgradeStartTime;
        this.upgradeEndTime = upgradeEndTime;
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

    public Object getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(Object loadStatus) {
        this.loadStatus = loadStatus;
    }

    public void setLoadStatus(LoadStatusEnum loadStatus) {
        this.loadStatus = loadStatus;
    }

    public ConfigBO getConfigBO() {
        return configBO;
    }

    public void setConfigBO(ConfigBO configBO) {
        this.configBO = configBO;
    }

    public Date getUpgradeStartTime() {
        return upgradeStartTime;
    }

    public void setUpgradeStartTime(Date upgradeStartTime) {
        this.upgradeStartTime = upgradeStartTime;
    }

    public Date getUpgradeEndTime() {
        return upgradeEndTime;
    }

    public void setUpgradeEndTime(Date upgradeEndTime) {
        this.upgradeEndTime = upgradeEndTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"loadStatus\":")
                .append(loadStatus);
        sb.append(",\"configBO\":")
                .append(configBO);
        sb.append(",\"upgradeStartTime\":\"")
                .append(upgradeStartTime).append('\"');
        sb.append(",\"upgradeEndTime\":\"")
                .append(upgradeEndTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
