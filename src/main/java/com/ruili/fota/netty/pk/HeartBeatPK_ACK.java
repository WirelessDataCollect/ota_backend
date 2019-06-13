package com.ruili.fota.netty.pk;

public class HeartBeatPK_ACK {
    private final String type = CommandType.HEARTBEAT_ACK.getType();
    private String imei;

    public HeartBeatPK_ACK(String imei) {
        this.imei = imei;
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
