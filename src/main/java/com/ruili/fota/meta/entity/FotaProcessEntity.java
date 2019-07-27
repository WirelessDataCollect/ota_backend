package com.ruili.fota.meta.entity;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.meta.bo.ConfigBO;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.Date;

/**
 * @author: liangjingxiong
 * @date: 2019-05-03
 * @description: 存储设备的烧录状态实体，与imei号为关系存储在cocurrentMap 在实体中，同时存入了设备需要烧录的固件信息，解决Netty异步的通信问题
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FotaProcessEntity implements Serializable {
    private static final long serialVersionUID = 4740806840734399176L;

    /**
     * imei信息
     */
    private String imei;
    /**
     * 进度查询的请求id
     */
    private String requestId;
    /**
     * 固件唯一id
     */
    private String firmwareId;
    /**
     * 当前固件下载包排序number
     */
    private int packNumber;
    /**
     * 总包数量
     */
    private int totalPack;
    /**
     * 供查询的下载状态阶段
     */
    private LoadStatusEnum statusEnum;
    /**
     * 携带本次升级配置上下文
     */
    private ConfigBO configBO;
    /**
     * 开始升级时间
     */
    private Date startTime;
    /**
     * 结束升级时间
     */
    private Date endTime;
    /**
     * 下载配置时间
     */
    private Date configTime;
    /**
     * 租户隔离的租户id
     */
    private String tenantId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"imei\":\"")
            .append(imei).append('\"');
        sb.append(",\"requestId\":\"")
            .append(requestId).append('\"');
        sb.append(",\"firmwareId\":\"")
            .append(firmwareId).append('\"');
        sb.append(",\"packNumber\":")
            .append(packNumber);
        sb.append(",\"totalPack\":")
            .append(totalPack);
        sb.append(",\"statusEnum\":")
            .append(statusEnum);
        sb.append(",\"configBO\":")
            .append(configBO);
        sb.append(",\"startTime\":\"")
            .append(startTime).append('\"');
        sb.append(",\"endTime\":\"")
            .append(endTime).append('\"');
        sb.append(",\"configTime\":\"")
            .append(configTime).append('\"');
        sb.append(",\"tenantId\":\"")
            .append(tenantId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
