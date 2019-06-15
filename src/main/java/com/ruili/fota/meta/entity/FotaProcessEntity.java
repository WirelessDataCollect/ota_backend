package com.ruili.fota.meta.entity;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.meta.bo.ConfigBO;
import io.netty.buffer.ByteBuf;

import java.io.*;
import java.util.Date;

/**
 * @author: liangjingxiong
 * @date: 2019-05-03
 * @description:存储设备的烧录状态实体，与imei号为关系存储在cocurrentMap 在实体中，同时存入了设备需要烧录的固件信息，解决Netty异步的通信问题
 */
public class FotaProcessEntity implements Serializable {

    private String imei;
    private String requestId;//记录本次请求的id
    private String firmwareId;
    private int packNumber;
    private int totalPack;
    private LoadStatusEnum statusEnum;
    private ConfigBO configBO;
    private Date startTime;
    private Date endTime;
    private Date configTime;

    public FotaProcessEntity(String imei, String requestId, String firmwareId, int packNumber, int totalPack, LoadStatusEnum statusEnum, ConfigBO configBO, Date startTime, Date endTime, Date configTime) {
        this.imei = imei;
        this.requestId = requestId;
        this.firmwareId = firmwareId;
        this.packNumber = packNumber;
        this.totalPack = totalPack;
        this.statusEnum = statusEnum;
        this.configBO = configBO;
        this.startTime = startTime;
        this.endTime = endTime;
        this.configTime = configTime;
    }

    public FotaProcessEntity() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getFirmwareId() {
        return firmwareId;
    }

    public void setFirmwareId(String firmwareId) {
        this.firmwareId = firmwareId;
    }

    public int getPackNumber() {
        return packNumber;
    }

    public void setPackNumber(int packNumber) {
        this.packNumber = packNumber;
    }

    public int getTotalPack() {
        return totalPack;
    }

    public void setTotalPack(int totalPack) {
        this.totalPack = totalPack;
    }

    public LoadStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(LoadStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public ConfigBO getConfigBO() {
        return configBO;
    }

    public void setConfigBO(ConfigBO configBO) {
        this.configBO = configBO;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getConfigTime() {
        return configTime;
    }

    public void setConfigTime(Date configTime) {
        this.configTime = configTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"requestId\":\"")
                .append(requestId).append('\"');
        sb.append(",\"firmwareId\":\"")
                .append(firmwareId).append('\"');
        sb.append(",\"packNumber\":")
                .append(packNumber);
        sb.append(",\"totalPack\":")
                .append(totalPack);
        sb.append(",\"statusEnum\":")
                .append(statusEnum);
        sb.append(",\"configBO\":")
                .append(configBO);
        sb.append(",\"startTime\":\"")
                .append(startTime).append('\"');
        sb.append(",\"endTime\":\"")
                .append(endTime).append('\"');
        sb.append(",\"configTime\":\"")
                .append(configTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
