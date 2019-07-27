package com.ruili.fota.meta.vo;

import java.io.Serializable;

import com.ruili.fota.meta.po.FotaImages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "固件列表")
public class OtaFileVO implements Serializable {

    private static final long serialVersionUID = -2493475437771397544L;
    @ApiModelProperty(value = "固件id")
    private String firmwareId;
    @ApiModelProperty(value = "固件名称")
    private String fileName;
    @ApiModelProperty(value = "目标设备类型mcuType")
    private String mcuType;
    @ApiModelProperty(value = "上传者")
    private String uploader;
    @ApiModelProperty(value = "上传者手机号码")
    private String uploaderTel;
    @ApiModelProperty(value = "固件版本号")
    private String firmVersion;
    @ApiModelProperty(value = "固件备注")
    private String content;

    public OtaFileVO(FotaImages images) {
        this.firmwareId = images.getFirmwareId();
        this.fileName = images.getFileName();
        this.mcuType = images.getMcuType();
        this.uploader = images.getUploader();
        this.uploaderTel = images.getUploadertel();
        this.firmVersion = images.getFirmVersion();
        this.content = images.getContent();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"firmwareId\":\"")
            .append(firmwareId).append('\"');
        sb.append(",\"fileName\":\"")
            .append(fileName).append('\"');
        sb.append(",\"mcuType\":\"")
            .append(mcuType).append('\"');
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
