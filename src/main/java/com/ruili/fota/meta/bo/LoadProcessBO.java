package com.ruili.fota.meta.bo;

import java.io.Serializable;

import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.meta.entity.FotaProcessEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: liangjingxiong
* @date: 2019-05-13
* @description: 记录设备固件升级状态，返回给前端，为FotaProcessEntity的前端简化版本
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoadProcessBO implements Serializable {

    private static final long serialVersionUID = 896253105021221698L;
    /**
     * imei号
     */
    private String imei;
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 当前包排序number
     */
    private int packNumber;
    /**
     * 总包书
     */
    private int totalPack;
    /**
     * 下载状态
     */
    private LoadStatusEnum statusEnum;
    /**
     * 配置信息
     */
    private ConfigBO configBO;

    public LoadProcessBO(FotaProcessEntity entity) {
        this.imei = entity.getImei();
        this.requestId = entity.getRequestId();
        this.packNumber = entity.getPackNumber();
        this.totalPack = entity.getTotalPack();
        this.statusEnum = entity.getStatusEnum();
        this.configBO = entity.getConfigBO();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"requestId\":\"")
                .append(requestId).append('\"');
        sb.append(",\"packNumber\":")
                .append(packNumber);
        sb.append(",\"totalPack\":")
                .append(totalPack);
        sb.append(",\"statusEnum\":")
                .append(statusEnum);
        sb.append(",\"configBO\":")
                .append(configBO);
        sb.append('}');
        return sb.toString();
    }
}
