package com.ruili.fota.meta.bo;

public class ConfigBO {
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
    private String cannum;
    /**
     * 升级方法，离线或在线，在数据库中进行记录
     */
    private String measure;
    /**
     * 升级的固件id
     */
    private String firmwareId;
    /**
     * mcu类型
     */
    private String mcuType;

    public ConfigBO(String recID, String sendID, String imei, String cannum, String measure, String firmwareId, String mcuType) {
        RecID = recID;
        SendID = sendID;
        this.imei = imei;
        this.cannum = cannum;
        this.measure = measure;
        this.firmwareId = firmwareId;
        this.mcuType = mcuType;
    }

    public ConfigBO() {
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

    public String getCannum() {
        return cannum;
    }

    public void setCannum(String cannum) {
        this.cannum = cannum;
    }

    public String getFirmwareId() {
        return firmwareId;
    }

    public void setFirmwareId(String firmwareId) {
        this.firmwareId = firmwareId;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getMcuType() {
        return mcuType;
    }

    public void setMcuType(String mcuType) {
        this.mcuType = mcuType;
    }

    @Override
    public String toString() {
        return "ConfigBO{" +
                "RecID='" + RecID + '\'' +
                ", SendID='" + SendID + '\'' +
                ", imei='" + imei + '\'' +
                ", cannum='" + cannum + '\'' +
                ", measure='" + measure + '\'' +
                ", firmwareId='" + firmwareId + '\'' +
                ", mcuType='" + mcuType + '\'' +
                '}';
    }
}
