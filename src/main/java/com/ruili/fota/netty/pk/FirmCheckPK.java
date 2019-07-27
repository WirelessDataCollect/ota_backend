package com.ruili.fota.netty.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: liangjingxiong
* @date: 2019-05-10
* @description: 下发给设备固件文件校验包，没下载50k校验一次
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirmCheckPK implements Serializable {
    private static final long serialVersionUID = -5008507530162320504L;
    /**
     * 声明包类型
     */
    private final String type = CommandType.FIRMWARE_CHECK.getType();
    /**
     * 包数据的md5
     */
    private String md5;
    /**
     * 当前包排序值
     */
    private int packNum;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"md5\":\"")
                .append(md5).append('\"');
        sb.append(",\"packNum\":")
                .append(packNum);
        sb.append('}');
        return sb.toString();
    }
}
