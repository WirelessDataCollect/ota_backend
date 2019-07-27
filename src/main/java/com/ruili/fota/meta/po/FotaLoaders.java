package com.ruili.fota.meta.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fota_loaders")
public class FotaLoaders implements Serializable {

    private static final long serialVersionUID = -1860075290241604820L;

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