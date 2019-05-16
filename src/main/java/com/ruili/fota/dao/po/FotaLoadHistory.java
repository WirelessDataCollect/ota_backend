package com.ruili.fota.dao.po;

import com.ruili.fota.dao.entity.FotaProcessEntity;

import java.util.Date;

public class FotaLoadHistory {
    private Integer gid;

    private String imei;

    private String requestId;

    private String firmwareId;

    private String loadProcess;

    private String configBo;

    private Date gmrcreate;

    private Date gmtupdate;

    private Date gmtmodified;

    public FotaLoadHistory(Integer gid, String imei, String requestId, String firmwareId, String loadProcess, String configBo, Date gmrcreate, Date gmtupdate, Date gmtmodified) {
        this.gid = gid;
        this.imei = imei;
        this.requestId = requestId;
        this.firmwareId = firmwareId;
        this.loadProcess = loadProcess;
        this.configBo = configBo;
        this.gmrcreate = gmrcreate;
        this.gmtupdate = gmtupdate;
        this.gmtmodified = gmtmodified;
    }

    public FotaLoadHistory(FotaProcessEntity entity) {
        this.imei = entity.getImei();
        this.requestId = entity.getRequestId();
        this.firmwareId = entity.getFirmwareId();
        this.loadProcess = entity.getStatusEnum().toString();
        this.configBo = entity.getConfigBO().toString();
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

    public Date getGmrcreate() {
        return gmrcreate;
    }

    public void setGmrcreate(Date gmrcreate) {
        this.gmrcreate = gmrcreate;
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
}