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
public class ConfigPK_ACK implements Serializable {
    private static final long serialVersionUID = 8702547047675942840L;

    /**
     * 申明包类型
     */
    private final String type = CommandType.CONFIG_ACK.getType();
    /**
     * 携带imei号信息
     */
    private String imei;
}
