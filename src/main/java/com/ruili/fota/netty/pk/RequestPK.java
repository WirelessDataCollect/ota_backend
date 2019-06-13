package com.ruili.fota.netty.pk;

/**
 * 设备向平台请求新的固件分片
 */
public class RequestPK {
    private final String type = CommandType.REQUEST_PACK.getType();
    private String imei;
    private int packnum;

    public RequestPK(String imei, int packnum) {
        this.imei = imei;
        this.packnum = packnum;
    }

    public RequestPK() {
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

    public int getPacknum() {
        return packnum;
    }

    public void setPacknum(int packnum) {
        this.packnum = packnum;
    }

    @Override
    public String toString() {
        return "RequestPK{" +
                "type='" + type + '\'' +
                ", imei='" + imei + '\'' +
                ", packnum=" + packnum +
                '}';
    }
}
