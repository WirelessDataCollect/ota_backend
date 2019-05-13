package com.ruili.fota.dao.bo;

import com.ruili.fota.constant.LoadStatusEnum;

import java.util.Date;

public class ConfigResPO {
    /**
     * 用来查询设备升级的唯一Id，放置在数据库中
     */
    private String requestId;
    /**
     * 设备下发配置的响应包内容
     */
    private LoadStatusEnum loadStatusEnum;

    public ConfigResPO(String requestId, LoadStatusEnum loadStatusEnum) {
        this.requestId = requestId;
        this.loadStatusEnum = loadStatusEnum;
    }

    public ConfigResPO() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LoadStatusEnum getLoadStatusEnum() {
        return loadStatusEnum;
    }

    public void setLoadStatusEnum(LoadStatusEnum loadStatusEnum) {
        this.loadStatusEnum = loadStatusEnum;
    }

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
