package com.ruili.fota.constant;

/**
 * enum class for device burn-flash status storage
 */
public enum LoadStatusEnum {
    LOAD_SUCCESS("烧写成功", 66),
    LOAD_ERROR("烧写失败", 23),
    LOAD_NO_STATUS("设备无烧写状态", 0),
    LOAD_ON_STATUS("设备正在烧写状态", 67),
    LOAD_FIRMWARE_NOT_FOUND("未找到设备固件", 401),
    LOAD_FIRMWARE_DEVICE_DISCONNECT("烧写固件时设备离线", 402),
    //下发配置指令时将设备添加到下发map中，若没有在map中，则报错
    LOAD_FIRMWARE_DEFORE_CONFIG("烧写固件前没有进行配置", 403),
    LOAD_FIRMWARE_DEVICE_NO_ACK("烧写固件时设备无ACK应答", 404),

    //配置相关的
    CONFIG_SUCCESS("下发配置成功，等待上报配置结果", 66),
    CONFIG_ERROR("下发配置失败", 23),
    CONFIG_NO_STATUS("无配置状态", 0),
    CONFIG_DEVICE_DISCONNECT("设备掉线", 402),

    //设备烧录过程相关的
    CAN_SEND_ERROR("CAN发送失败", 100),
    CAN_NO_REPLY("未收到CAN回复", 101),
    ERROR_FRAME_RECEIVED("接收到协议外错误帧", 102),
    DECRYPT_ERROR("解密失败", 103),
    FILE_WRONG("文件错误", 104),

    //状态不在枚举中
    NOT_FOUND_CODE("code无法识别", 404);

    final String status;
    final int code;

    LoadStatusEnum(String msg, int code) {
        this.status = msg;
        this.code = code;
    }

    public LoadStatusEnum searchByCode(int code) {
        for (LoadStatusEnum statusEnum : LoadStatusEnum.values()) {
            if (statusEnum.code == code) return statusEnum;
        }
        return LoadStatusEnum.NOT_FOUND_CODE;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"status\":\"")
                .append(status).append('\"');
        sb.append(",\"code\":")
                .append(code);
        sb.append('}');
        return sb.toString();
    }
}
