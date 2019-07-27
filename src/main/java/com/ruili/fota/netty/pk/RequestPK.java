package com.ruili.fota.netty.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备向平台请求新的固件分片
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPK implements Serializable {
    private static final long serialVersionUID = -8836411191316139431L;
    /**
     * 声明包类型
     */
    private final String type = CommandType.REQUEST_PACK.getType();
    /**
     * 设备imei
     */
    private String imei;
    /**
     * 请求第packnum的包信息
     */
    private int packnum;
}
