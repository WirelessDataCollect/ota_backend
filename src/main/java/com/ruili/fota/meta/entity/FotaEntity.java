package com.ruili.fota.meta.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.InputStream;

/**
* @author: liangjingxiong
* @date: 2019-04-09
* @description:
 * 固件实体信息
*/
@JsonIgnoreProperties({"bytes"})
public class FotaEntity {
    private String fileName;
    private String fileRealName;
    private long fileSize;
    private String fileType;
    private InputStream inputStream;

    public FotaEntity(String fileName, String fileRealName, long fileSize, String fileType, InputStream inputStream) {
        this.fileName = fileName;
        this.fileRealName = fileRealName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.inputStream = inputStream;
    }

    public FotaEntity() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"fileName\":\"")
                .append(fileName).append('\"');
        sb.append(",\"fileRealName\":\"")
                .append(fileRealName).append('\"');
        sb.append(",\"fileSize\":")
                .append(fileSize);
        sb.append(",\"fileType\":\"")
                .append(fileType).append('\"');
        sb.append(",\"inputStream\":")
                .append(inputStream);
        sb.append('}');
        return sb.toString();
    }
}
