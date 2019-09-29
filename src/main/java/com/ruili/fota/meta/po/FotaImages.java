package com.ruili.fota.meta.po;

import com.alibaba.fastjson.annotation.JSONField;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruili.fota.common.DateTools;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @description:
 * @author: jingxiong.ljx
 * @date: 2019-07-27
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fota_images")
public class FotaImages implements Serializable {

    private static final long serialVersionUID = 1888069127360784553L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer gid;

    @Id
    @Column(name = "firmware_id")
    private String firmwareId;

    /**
     * 下发的设备类型，单通道，双通道，中央控制器
     */
    @Column(name = "mcu_type")
    private String mcuType;

    /**
     * 固件的文件名
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 上传者姓名
     */
    private String uploader;

    /**
     * 上传者手机号
     */
    private String uploadertel;

    /**
     * 固件版本号
     */
    @Column(name = "firm_version")
    private String firmVersion;

    /**
     * 信息说明
     */
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtcreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtupdate;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
            .append(gid);
        sb.append(",\"firmwareId\":\"")
            .append(firmwareId).append('\"');
        sb.append(",\"mcuType\":\"")
            .append(mcuType).append('\"');
        sb.append(",\"fileName\":\"")
            .append(fileName).append('\"');
        sb.append(",\"uploader\":\"")
            .append(uploader).append('\"');
        sb.append(",\"uploadertel\":\"")
            .append(uploadertel).append('\"');
        sb.append(",\"firmVersion\":\"")
            .append(firmVersion).append('\"');
        sb.append(",\"content\":\"")
            .append(content).append('\"');
        sb.append(",\"gmtcreate\":\"")
            .append(DateTools.DateToString(gmtcreate, "yyyy-MM-dd HH:mm:ss")).append('\"');
        sb.append(",\"gmtupdate\":\"")
            .append(DateTools.DateToString(gmtupdate, "yyyy-MM-dd HH:mm:ss")).append('\"');
        sb.append('}');
        return sb.toString();
    }
}