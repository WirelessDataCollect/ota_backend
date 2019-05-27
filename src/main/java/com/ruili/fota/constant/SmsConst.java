package com.ruili.fota.constant;

/**
 * 短信服务相关参数
 */
public class SmsConst {

    public static final String accessKeyId = "LTAI5UxB4b0A7P3K";

    public static final String accessKeySecret = "RuwehlW8IrEPwU6khqzK2k6NFDmwS2";

    public static final String CH_SIGNATURE = "钛比监测";

    /**
     * 模版内容
     * <p>
     * 维护通知，${realName}，位于${deviceLocation}的${deviceId}号设备于${eventTime}出现${eventMsg}事件，请您尽快处理。
     */
    public static final String CH_TEMPLATE = "SMS_139976448";

    public static final String EN_SIGNATURE = "";

    public static final String EN_TEMPLATE = "";


    public static final String SMS_SEND_STATUS_OK = "OK";
    public static final String SMS_IDENTIFY_CODE = "IDENTIFY";
    public static final String SMS_IDENTIFY_TEMPLATE = "SMS_154500719";
}
