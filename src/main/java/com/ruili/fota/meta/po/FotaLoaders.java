package com.ruili.fota.meta.po;

import java.util.Date;

public class FotaLoaders {
    private Integer gid;

    private String imei;

    private String imsi;

    private Integer csq;

    private Integer loadStatus;

    private Integer onlineStatus;

    private Date gmtcreate;

    private Date gmtupdate;

    private Date gmtmodified;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    public Integer getCsq() {
        return csq;
    }

    public void setCsq(Integer csq) {
        this.csq = csq;
    }

    public Integer getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Date getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(Date gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public Date getGmtupdate() {
        return gmtupdate;
    }

    public void setGmtupdate(Date gmtupdate) {
        this.gmtupdate = gmtupdate;
    }

    public Date getGmtmodified() {
        return gmtmodified;
    }

    public void setGmtmodified(Date gmtmodified) {
        this.gmtmodified = gmtmodified;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
                .append(gid);
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"imsi\":\"")
                .append(imsi).append('\"');
        sb.append(",\"csq\":")
                .append(csq);
        sb.append(",\"loadStatus\":")
                .append(loadStatus);
        sb.append(",\"onlineStatus\":")
                .append(onlineStatus);
        sb.append(",\"gmtcreate\":\"")
                .append(gmtcreate).append('\"');
        sb.append(",\"gmtupdate\":\"")
                .append(gmtupdate).append('\"');
        sb.append(",\"gmtmodified\":\"")
                .append(gmtmodified).append('\"');
        sb.append('}');
        return sb.toString();
    }
}