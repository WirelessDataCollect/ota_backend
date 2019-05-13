package com.ruili.fota.netty.pk;

public class ConfigPK_ACK {
    private final String type = "configok";
    private String imei;

    public ConfigPK_ACK(String imei) {
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
        return "ConfigPK_ACK{" +
                "type='" + type + '\'' +
                ", imei='" + imei + '\'' +
                '}';
    }
}
