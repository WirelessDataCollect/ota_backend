package com.ruili.fota.meta.vo;

import com.ruili.fota.meta.po.FotaImages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "固件列表")
public class OtaFileVO {

    @ApiModelProperty(value = "固件id")
    private String firmwareId;
    @ApiModelProperty(value = "上传者")
    private String uploader;
    @ApiModelProperty(value = "上传者手机号码")
    private String uploaderTel;
    @ApiModelProperty(value = "固件版本号")
    private String firmVersion;
    @ApiModelProperty(value = "固件备注")
    private String content;

    public OtaFileVO(String firmwareId, String uploader, String uploaderTel, String firmVersion, String content) {
        this.firmwareId = firmwareId;
        this.uploader = uploader;
        this.uploaderTel = uploaderTel;
        this.firmVersion = firmVersion;
        this.content = content;
    }

    public OtaFileVO(FotaImages images) {
        this.firmwareId = images.getFirmwareId();
        this.uploader = images.getUploader();
        this.uploaderTel = images.getUploadertel();
        this.firmVersion = images.getFirmVersion();
        this.content = images.getContent();
    }

    public OtaFileVO() {
    }

    public String getFirmwareId() {
        return firmwareId;
    }

    public void setFirmwareId(String firmwareId) {
        this.firmwareId = firmwareId;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getUploaderTel() {
        return uploaderTel;
    }

    public void setUploaderTel(String uploaderTel) {
        this.uploaderTel = uploaderTel;
    }

    public String getFirmVersion() {
        return firmVersion;
    }

    public void setFirmVersion(String firmVersion) {
        this.firmVersion = firmVersion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"firmwareId\":\"")
                .append(firmwareId).append('\"');
        sb.append(",\"uploader\":\"")
                .append(uploader).append('\"');
        sb.append(",\"uploaderTel\":\"")
                .append(uploaderTel).append('\"');
        sb.append(",\"firmVersion\":\"")
                .append(firmVersion).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
