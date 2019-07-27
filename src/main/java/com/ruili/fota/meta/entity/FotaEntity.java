package com.ruili.fota.meta.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.io.Serializable;

/**
* @author: liangjingxiong
* @date: 2019-04-09
* @description:
 * 固件实体信息
*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"bytes"})
public class FotaEntity implements Serializable {
    private static final long serialVersionUID = -7605754369035161203L;

    /**
     * 文件名（mongodb中文件名）
     */
    private String fileName;
    /**
     * 文件原名，真实名称
     */
    private String fileRealName;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件字节流
     */
    private InputStream inputStream;

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
