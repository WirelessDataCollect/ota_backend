package com.ruili.fota.netty.pk;

/**
 * 平台下发给设备的配置信息包
 */
public class ConfigPK {
    private final String type = "config";
    private int RecID;
    private int SendID;
    private String imei;
    private int cannum;
    private int measure;
    private int packSize;

    public ConfigPK(int recID, int sendID, String imei, int cannum, int measure, int packSize) {
        RecID = recID;
        SendID = sendID;
        this.imei = imei;
        this.cannum = cannum;
        this.measure = measure;
        this.packSize = packSize;
    }

    public ConfigPK() {
    }

    public String getType() {
        return type;
    }

    public int getRecID() {
        return RecID;
    }

    public void setRecID(int recID) {
        RecID = recID;
    }

    public int getSendID() {
        return SendID;
    }

    public void setSendID(int sendID) {
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
