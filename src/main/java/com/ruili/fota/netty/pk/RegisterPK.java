package com.ruili.fota.netty.pk;

/**
 * 设备上报注册包
 */
public class RegisterPK {
    private final String typ = "register";
    private String imei;
    private String imsi;
    private int csq;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public int getCsq() {
        return csq;
    }

    public void setCsq(int csq) {
        this.csq = csq;
    }

    public RegisterPK(String imei, String imsi, int csq) {
        this.imei = imei;
        this.imsi = imsi;
        this.csq = csq;
    }

    @Override
    public String toString() {
        return "RegisterPK{" +
                "typ='" + typ + '\'' +
                ", imei='" + imei + '\'' +
                ", imsi='" + imsi + '\'' +
                ", csq=" + csq +
                '}';
    }
}
