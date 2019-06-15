package com.ruili.fota.meta.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruili.fota.common.DateTools;
import com.ruili.fota.meta.entity.FotaProcessEntity;

import java.util.Date;

public class FotaLoadHistory {
    private Integer gid;

    private String imei;

    private String requestId;

    private String firmwareId;

    private String loadProcess;

    private String configBo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtcreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtupdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtmodified;

    public FotaLoadHistory(Integer gid, String imei, String requestId, String firmwareId, String loadProcess, String configBo, Date gmtcreate, Date gmtupdate, Date gmtmodified) {
        this.gid = gid;
        this.imei = imei;
        this.requestId = requestId;
        this.firmwareId = firmwareId;
        this.loadProcess = loadProcess;
        this.configBo = configBo;
        this.gmtcreate = gmtcreate;
        this.gmtupdate = gmtupdate;
        this.gmtmodified = gmtmodified;
    }

    public FotaLoadHistory(FotaProcessEntity entity) {
        this.imei = entity.getImei();
        this.requestId = entity.getRequestId();
        this.firmwareId = entity.getFirmwareId();
        this.loadProcess = entity.getStatusEnum().toString();
        this.configBo = entity.getConfigBO().toString();
        this.gmtcreate = DateTools.currentTime();
        this.gmtupdate = entity.getStartTime();
        this.gmtmodified = entity.getEndTime();
    }

    public FotaLoadHistory() {
    }

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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getFirmwareId() {
        return firmwareId;
    }

    public void setFirmwareId(String firmwareId) {
        this.firmwareId = firmwareId == null ? null : firmwareId.trim();
    }

    public String getLoadProcess() {
        return loadProcess;
    }

    public void setLoadProcess(String loadProcess) {
        this.loadProcess = loadProcess == null ? null : loadProcess.trim();
    }

    public String getConfigBo() {
        return configBo;
    }

    public void setConfigBo(String configBo) {
        this.configBo = configBo == null ? null : configBo.trim();
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
        sb.append(",\"requestId\":\"")
                .append(requestId).append('\"');
        sb.append(",\"firmwareId\":\"")
                .append(firmwareId).append('\"');
        sb.append(",\"loadProcess\":\"")
                .append(loadProcess).append('\"');
        sb.append(",\"configBo\":\"")
                .append(configBo).append('\"');
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