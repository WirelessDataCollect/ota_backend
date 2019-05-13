package com.ruili.fota.dao.entity;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.dao.bo.ConfigBO;
import io.netty.buffer.ByteBuf;

/**
* @author: liangjingxiong
* @date: 2019-05-03
* @description:存储设备的烧录状态实体，与imei号为关系存储在cocurrentMap
 * 在实体中，同时存入了设备需要烧录的固件信息，解决Netty异步的通信问题
*/
public class FotaProcessEntity {

    private String imei;
    private String requestId;//记录本次请求的id
    private int packNumber;
    private int totalPack;
    private LoadStatusEnum statusEnum;
    private ByteBuf firmwareByteBuf;
    private ConfigBO configBO;


    public FotaProcessEntity(String imei, String requestId, int packNumber, int totalPack, LoadStatusEnum statusEnum, ByteBuf firmwareByteBuf, ConfigBO configBO) {
        this.imei = imei;
        this.requestId = requestId;
        this.packNumber = packNumber;
        this.totalPack = totalPack;
        this.statusEnum = statusEnum;
        this.firmwareByteBuf = firmwareByteBuf;
        this.configBO = configBO;
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

    public ByteBuf getFirmwareByteBuf() {
        return firmwareByteBuf;
    }

    public void setFirmwareByteBuf(ByteBuf firmwareByteBuf) {
        this.firmwareByteBuf = firmwareByteBuf;
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
        sb.append(",\"firmwareByteBuf\":")
                .append(firmwareByteBuf);
        sb.append(",\"configBO\":")
                .append(configBO);
        sb.append('}');
        return sb.toString();
    }
}
