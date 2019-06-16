package com.ruili.fota.meta.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "fota_images")
public class FotaImages implements Serializable {
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


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtcreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtupdate;

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
     * @return firmware_id
     */
    public String getFirmwareId() {
        return firmwareId;
    }

    /**
     * @param firmwareId
     */
    public void setFirmwareId(String firmwareId) {
        this.firmwareId = firmwareId == null ? null : firmwareId.trim();
    }

    /**
     * 获取下发的设备类型，单通道，双通道，中央控制器
     *
     * @return mcu_type - 下发的设备类型，单通道，双通道，中央控制器
     */
    public String getMcuType() {
        return mcuType;
    }

    /**
     * 设置下发的设备类型，单通道，双通道，中央控制器
     *
     * @param mcuType 下发的设备类型，单通道，双通道，中央控制器
     */
    public void setMcuType(String mcuType) {
        this.mcuType = mcuType == null ? null : mcuType.trim();
    }

    /**
     * 获取固件的文件名
     *
     * @return file_name - 固件的文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置固件的文件名
     *
     * @param fileName 固件的文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 获取上传者姓名
     *
     * @return uploader - 上传者姓名
     */
    public String getUploader() {
        return uploader;
    }

    /**
     * 设置上传者姓名
     *
     * @param uploader 上传者姓名
     */
    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    /**
     * 获取上传者手机号
     *
     * @return uploadertel - 上传者手机号
     */
    public String getUploadertel() {
        return uploadertel;
    }

    /**
     * 设置上传者手机号
     *
     * @param uploadertel 上传者手机号
     */
    public void setUploadertel(String uploadertel) {
        this.uploadertel = uploadertel == null ? null : uploadertel.trim();
    }

    /**
     * 获取固件版本号
     *
     * @return firm_version - 固件版本号
     */
    public String getFirmVersion() {
        return firmVersion;
    }

    /**
     * 设置固件版本号
     *
     * @param firmVersion 固件版本号
     */
    public void setFirmVersion(String firmVersion) {
        this.firmVersion = firmVersion == null ? null : firmVersion.trim();
    }

    /**
     * 获取信息说明
     *
     * @return content - 信息说明
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置信息说明
     *
     * @param content 信息说明
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return gmtcreate
     */
    public Date getGmtcreate() {
        return gmtcreate;
    }

    /**
     * @param gmtcreate
     */
    public void setGmtcreate(Date gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    /**
     * @return gmtupdate
     */
    public Date getGmtupdate() {
        return gmtupdate;
    }

    /**
     * @param gmtupdate
     */
    public void setGmtupdate(Date gmtupdate) {
        this.gmtupdate = gmtupdate;
    }

    public FotaImages(String firmwareId, String mcuType, String fileName, String uploader, String uploadertel, String firmVersion, String content, Date gmtcreate, Date gmtupdate) {
        this.firmwareId = firmwareId;
        this.mcuType = mcuType;
        this.fileName = fileName;
        this.uploader = uploader;
        this.uploadertel = uploadertel;
        this.firmVersion = firmVersion;
        this.content = content;
        this.gmtcreate = gmtcreate;
        this.gmtupdate = gmtupdate;
    }

    public FotaImages() {
    }

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
                .append(gmtcreate).append('\"');
        sb.append(",\"gmtupdate\":\"")
                .append(gmtupdate).append('\"');
        sb.append('}');
        return sb.toString();
    }


}