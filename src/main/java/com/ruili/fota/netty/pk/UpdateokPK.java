package com.ruili.fota.netty.pk;

/**
 * 平台下发给设备的配置信息包
 */
public class UpdateokPK {
    private final String type = "updateok";
    private String imei;

    public UpdateokPK(String imei) {
        this.imei = imei;
    }

    public UpdateokPK() {
    }

    public String getType() {
        return type;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
