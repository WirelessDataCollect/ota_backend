package com.ruili.fota.meta.po;

import java.util.Date;

public class FotaImages {
    private Integer gid;

    private String firmwareId;

    private String uploader;

    private String uploadertel;

    private String firmVersion;

    private String content;

    private Date gmtcreate;

    private Date gmtupdate;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getFirmwareId() {
        return firmwareId;
    }

    public void setFirmwareId(String firmwareId) {
        this.firmwareId = firmwareId == null ? null : firmwareId.trim();
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    public String getUploadertel() {
        return uploadertel;
    }

    public void setUploadertel(String uploadertel) {
        this.uploadertel = uploadertel == null ? null : uploadertel.trim();
    }

    public String getFirmVersion() {
        return firmVersion;
    }

    public void setFirmVersion(String firmVersion) {
        this.firmVersion = firmVersion == null ? null : firmVersion.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
                .append(gid);
        sb.append(",\"firmwareId\":\"")
                .append(firmwareId).append('\"');
        sb.append(",\"uploader\":\"")
                .append(uploader).append('\"');
        sb.append(",\"uploadertel\":\"")
                .append(uploadertel).append('\"');
        sb.append(",\"firmVersion\":\"")
                .append(firmVersion).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"gmtcreate\":\"")
                .append(gmtcreate).append('\"');
        sb.append(",\"gmtupdate\":\"")
                .append(gmtupdate).append('\"');
        sb.append('}');
        return sb.toString();
    }
}