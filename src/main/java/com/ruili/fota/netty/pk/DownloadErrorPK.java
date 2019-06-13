package com.ruili.fota.netty.pk;

public class DownloadErrorPK {

    private final String type = CommandType.UPDATE_ERROR.getType();
    private String imei;
    private int code;

    public DownloadErrorPK(String imei, int code) {
        this.imei = imei;
        this.code = code;
    }

    public DownloadErrorPK() {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"code\":")
                .append(code);
        sb.append('}');
        return sb.toString();
    }
}
