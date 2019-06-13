package com.ruili.fota.meta.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fota_loaders")
public class FotaLoaders {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer gid;

    @Id
    private String imei;

    private String imsi;

    /**
     * 用来判断设备升级状态
     */
    @Column(name = "request_id")
    private String requestId;

    private Integer csq;

    @Column(name = "load_status")
    private Integer loadStatus;

    @Column(name = "online_status")
    private Integer onlineStatus;

    /**
     * 采集器第一次上线时间
     */
    private Date gmtcreate;

    /**
     * 采集器刷新时间
     */
    private Date gmtupdate;

    private Date gmtmodified;

    /**
     * @return gid
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * @param gid
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * @return imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * @return imsi
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * @param imsi
     */
    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    /**
     * 获取用来判断设备升级状态
     *
     * @return request_id - 用来判断设备升级状态
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * 设置用来判断设备升级状态
     *
     * @param requestId 用来判断设备升级状态
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    /**
     * @return csq
     */
    public Integer getCsq() {
        return csq;
    }

    /**
     * @param csq
     */
    public void setCsq(Integer csq) {
        this.csq = csq;
    }

    /**
     * @return load_status
     */
    public Integer getLoadStatus() {
        return loadStatus;
    }

    /**
     * @param loadStatus
     */
    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }

    /**
     * @return online_status
     */
    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    /**
     * @param onlineStatus
     */
    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    /**
     * 获取采集器第一次上线时间
     *
     * @return gmtcreate - 采集器第一次上线时间
     */
    public Date getGmtcreate() {
        return gmtcreate;
    }

    /**
     * 设置采集器第一次上线时间
     *
     * @param gmtcreate 采集器第一次上线时间
     */
    public void setGmtcreate(Date gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    /**
     * 获取采集器刷新时间
     *
     * @return gmtupdate - 采集器刷新时间
     */
    public Date getGmtupdate() {
        return gmtupdate;
    }

    /**
     * 设置采集器刷新时间
     *
     * @param gmtupdate 采集器刷新时间
     */
    public void setGmtupdate(Date gmtupdate) {
        this.gmtupdate = gmtupdate;
    }

    /**
     * @return gmtmodified
     */
    public Date getGmtmodified() {
        return gmtmodified;
    }

    /**
     * @param gmtmodified
     */
    public void setGmtmodified(Date gmtmodified) {
        this.gmtmodified = gmtmodified;
    }

    public FotaLoaders(String imei, String imsi, String requestId, Integer csq, Integer loadStatus, Integer onlineStatus, Date gmtcreate, Date gmtupdate, Date gmtmodified) {
        this.imei = imei;
        this.imsi = imsi;
        this.requestId = requestId;
        this.csq = csq;
        this.loadStatus = loadStatus;
        this.onlineStatus = onlineStatus;
        this.gmtcreate = gmtcreate;
        this.gmtupdate = gmtupdate;
        this.gmtmodified = gmtmodified;
    }

    public FotaLoaders() {
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
        sb.append(",\"requestId\":\"")
                .append(requestId).append('\"');
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