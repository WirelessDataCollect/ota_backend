package com.ruili.fota.meta.bo;


import com.ruili.fota.meta.po.FotaImages;

import java.io.Serializable;

public class ConfigBO implements Serializable {
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


    public ConfigBO(String recID, String sendID, String imei, int cannum, int measure, String firmwareId, String mcuType) {
        RecID = recID;
        SendID = sendID;
        this.imei = imei;
        this.cannum = cannum;
        this.measure = measure;
        this.firmwareId = firmwareId;
        this.mcuType = mcuType;
    }

    public ConfigBO(String recID, String sendID, String imei, int cannum, int measure, String firmwareId, String mcuType, FotaImages fotaImages) {
        RecID = recID;
        SendID = sendID;
        this.imei = imei;
        this.cannum = cannum;
        this.measure = measure;
        this.firmwareId = firmwareId;
        this.mcuType = mcuType;
        this.fotaImages = fotaImages;
    }

    public ConfigBO() {
    }

    public FotaImages getFotaImages() {
        return fotaImages;
    }

    public void setFotaImages(FotaImages fotaImages) {
        this.fotaImages = fotaImages;
    }

    public String getRecID() {
        return RecID;
    }

    public void setRecID(String recID) {
        RecID = recID;
    }

    public String getSendID() {
        return SendID;
    }

    public void setSendID(String sendID) {
        SendID = sendID;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getCannum() {
        return cannum;
    }

    public void setCannum(int cannum) {
        this.cannum = cannum;
    }

    public int getMeasure() {
        return measure;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    public String getFirmwareId() {
        return firmwareId;
    }

    public void setFirmwareId(String firmwareId) {
        this.firmwareId = firmwareId;
    }

    public String getMcuType() {
        return mcuType;
    }

    public void setMcuType(String mcuType) {
        this.mcuType = mcuType;
    }

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
        sb.append('}');
        return sb.toString();
    }
}
