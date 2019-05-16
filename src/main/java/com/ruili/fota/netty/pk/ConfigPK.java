package com.ruili.fota.netty.pk;

/**
 * 平台下发给设备的配置信息包
 */
public class ConfigPK {
    private final String type = "config";
    private String RecID;
    private String SendID;
    private String imei;
    private String cannum;
    private String measure;
    private int packSize;

    public ConfigPK(String recID, String sendID, String imei, String cannum, String mesaure, int packSize) {
        RecID = recID;
        SendID = sendID;
        this.imei = imei;
        this.cannum = cannum;
        this.measure = mesaure;
        this.packSize = packSize;
    }

    public ConfigPK() {
    }

    public String getType() {
        return type;
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

    public String getMesaure() {
        return measure;
    }

    public void setMesaure(String mesaure) {
        this.measure = mesaure;
    }

    public int getPackSize() {
        return packSize;
    }

    public void setPackSize(int packSize) {
        this.packSize = packSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"RecID\":\"")
                .append(RecID).append('\"');
        sb.append(",\"SendID\":\"")
                .append(SendID).append('\"');
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"cannum\":\"")
                .append(cannum).append('\"');
        sb.append(",\"measure\":\"")
                .append(measure).append('\"');
        sb.append(",\"packSize\":")
                .append(packSize);
        sb.append('}');
        return sb.toString();
    }
}
