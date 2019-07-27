package com.ruili.fota.meta.bo;

import java.io.Serializable;

import com.ruili.fota.constant.LoadStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "下发配置后的返回")
public class ConfigResPO implements Serializable {
    private static final long serialVersionUID = 7724344324038837461L;
    /**
     * 用来查询设备升级的唯一Id，放置在数据库中
     */
    @ApiModelProperty(value = "用来查询设备升级的唯一Id，放置在数据库中")
    private String requestId;
    /**
     * 设备下发配置的响应包内容
     */
    @ApiModelProperty(value = "设备下发配置的接收状态")
    private LoadStatusEnum loadStatusEnum;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"requestId\":\"")
            .append(requestId).append('\"');
        sb.append(",\"loadStatusEnum\":")
            .append(loadStatusEnum);
        sb.append('}');
        return sb.toString();
    }
}
