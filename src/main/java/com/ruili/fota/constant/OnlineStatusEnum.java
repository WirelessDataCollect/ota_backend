package com.ruili.fota.constant;

public enum OnlineStatusEnum {

    /**
     * 设备在线
     */
    ONLINE_STATUS("设备在线",66),
    /**
     * 设备离线
     */
    OFFINE_STATUS("设备离线",23),
    /**
     * 设备待初始化无状态
     */
    DONT_KNOW_STATUS("设备待初始化无状态",0);

    final String msg;
    final int code;

    OnlineStatusEnum(String msg,int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
