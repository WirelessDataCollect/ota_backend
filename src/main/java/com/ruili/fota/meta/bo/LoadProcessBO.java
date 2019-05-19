package com.ruili.fota.meta.bo;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.meta.entity.FotaProcessEntity;

/**
* @author: liangjingxiong
* @date: 2019-05-13
* @description:记录设备固件升级状态，返回给前端，为FotaProcessEntity的前端简化版本
*/
public class LoadProcessBO {

    private String imei;
    private String requestId;//记录本次请求的id
    private int packNumber;
    private int totalPack;
    private LoadStatusEnum statusEnum;
    private ConfigBO configBO;

    public LoadProcessBO(FotaProcessEntity entity) {
        this.imei = entity.getImei();
        this.requestId = entity.getRequestId();
        this.packNumber = entity.getPackNumber();
        this.totalPack = entity.getTotalPack();
        this.statusEnum = entity.getStatusEnum();
        this.configBO = entity.getConfigBO();
    }

    public LoadProcessBO(String imei, String requestId, int packNumber, int totalPack, LoadStatusEnum statusEnum, ConfigBO configBO) {
        this.imei = imei;
        this.requestId = requestId;
        this.packNumber = packNumber;
        this.totalPack = totalPack;
        this.statusEnum = statusEnum;
        this.configBO = configBO;
    }

    public LoadProcessBO() {
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"requestId\":\"")
                .append(requestId).append('\"');
        sb.append(",\"packNumber\":")
                .append(packNumber);
        sb.append(",\"totalPack\":")
                .append(totalPack);
        sb.append(",\"statusEnum\":")
                .append(statusEnum);
        sb.append(",\"configBO\":")
                .append(configBO);
        sb.append('}');
        return sb.toString();
    }
}
