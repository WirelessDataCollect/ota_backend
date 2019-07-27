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
public class UpdateOkPK implements Serializable {
    private static final long serialVersionUID = -5450371374385754590L;
    /**
     * 申明包的类型
     */
    private final String type = CommandType.UPDATE_OK.getType();
    /**
     * 设备imei
     */
    private String imei;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
