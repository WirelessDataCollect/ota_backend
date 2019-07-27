package com.ruili.fota.meta.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.meta.bo.ConfigBO;
import com.ruili.fota.meta.po.FotaLoadHistory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "OTA历史记录")
public class OtaHistoryVO implements Serializable {

    private static final long serialVersionUID = -7161940826365164036L;

    @ApiModelProperty(value = "设备id")
    private Integer id;
    @ApiModelProperty(value = "设备imei")
    private String imei;
    @ApiModelProperty(value = "设备升级状态，66代表升级成功，23代表升级失败")
    private Object loadStatus;
    @ApiModelProperty(value = "固件的配置信息")
    private ConfigBO configBO;
    @ApiModelProperty(value = "固件升级开始时间")
    private Date upgradeStartTime;
    @ApiModelProperty(value = "固件升级结束时间")
    private Date upgradeEndTime;


    public OtaHistoryVO(FotaLoadHistory history) {
        this.id = history.getGid();
        this.imei = history.getImei();
        this.loadStatus = JSON.parseObject(history.getLoadProcess());
        this.configBO = JSON.parseObject(history.getConfigBo(), ConfigBO.class);
        this.upgradeStartTime = history.getGmtupdate();
        this.upgradeEndTime = history.getGmtmodified();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"imei\":\"")
                .append(imei).append('\"');
        sb.append(",\"loadStatus\":")
                .append(loadStatus);
        sb.append(",\"configBO\":")
                .append(configBO);
        sb.append(",\"upgradeStartTime\":\"")
                .append(upgradeStartTime).append('\"');
        sb.append(",\"upgradeEndTime\":\"")
                .append(upgradeEndTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
