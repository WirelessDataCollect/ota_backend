package com.ruili.fota.netty.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateErrorPK implements Serializable {
    private static final long serialVersionUID = -8305969965092295690L;
    /**
     * 申明包类型
     */
    private final String type = CommandType.UPDATE_ERROR.getType();
    /**
     * 设备imei号信息
     */
    private String imei;
    /**
     * 升级失败的状态码
     */
    private int code;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"code\":")
                .append(code);
        sb.append('}');
        return sb.toString();
    }
}
