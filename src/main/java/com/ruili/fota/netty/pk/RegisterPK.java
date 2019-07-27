package com.ruili.fota.netty.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备上报注册包
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPK implements Serializable {
    private static final long serialVersionUID = -3141595209231065477L;
    /**
     * 申明包类型
     */
    private final String typ = CommandType.RIGISTER.getType();
    /**
     * 设备imei
     */
    private String imei;
    /**
     * 设备imsi
     */
    private String imsi;
    /**
     * 设备信号强度，0到31
     */
    private int csq;
}
