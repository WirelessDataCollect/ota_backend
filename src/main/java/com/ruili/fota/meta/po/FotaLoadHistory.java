package com.ruili.fota.meta.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruili.fota.common.DateTools;
import com.ruili.fota.meta.entity.FotaProcessEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FotaLoadHistory implements Serializable {

    private static final long serialVersionUID = 4265415125843991016L;

    private Integer gid;

    private String imei;

    private String requestId;

    private String firmwareId;

    private String loadProcess;

    private String configBo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtcreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtupdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtmodified;

    /**
     * 租户id
     */
    private String tenantId;

    public FotaLoadHistory(FotaProcessEntity entity) {
        this.imei = entity.getImei();
        this.requestId = entity.getRequestId();
        this.firmwareId = entity.getFirmwareId();
        this.loadProcess = entity.getStatusEnum().toString();
        this.configBo = entity.getConfigBO().toString();
        this.gmtcreate = DateTools.currentTime();
        this.gmtupdate = entity.getStartTime();
        this.gmtmodified = entity.getEndTime();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gid\":")
            .append(gid);
        sb.append(",\"imei\":\"")
            .append(imei).append('\"');
        sb.append(",\"requestId\":\"")
            .append(requestId).append('\"');
        sb.append(",\"firmwareId\":\"")
            .append(firmwareId).append('\"');
        sb.append(",\"loadProcess\":\"")
            .append(loadProcess).append('\"');
        sb.append(",\"configBo\":\"")
            .append(configBo).append('\"');
        sb.append(",\"gmtcreate\":\"")
            .append(gmtcreate).append('\"');
        sb.append(",\"gmtupdate\":\"")
            .append(gmtupdate).append('\"');
        sb.append(",\"gmtmodified\":\"")
            .append(gmtmodified).append('\"');
        sb.append(",\"tenantId\":\"")
            .append(tenantId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}