package com.ruili.fota.netty.pk;

public enum CommandType {
    //设备注册包
    RIGISTER("register"),
    //注册回复包
    RIGISTER_ACK("regrepok"),
    //配置包
    CONFIG("config"),
    //配置ok包
    CONFIG_ACK("configok"),
    //设备主动请求包
    REQUEST_PACK("reqpack"),
    //下载完成包
    DOWMLOAD_OK("downloadok"),
    //下载失败包
    DOWMLOAD_ERROR("downloadfail"),
    //升级完成包
    UPDATE_OK("updateok"),
    //升级失败包
    UPDATE_ERROR("updatefail"),
    //心跳包
    HEARTBEAT("heartbeat"),
    //心跳包回复
    HEARTBEAT_ACK("heartrsp"),
    //固件下发验证包
    FIRMWARE_CHECK("check");

    final String type;

    CommandType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
