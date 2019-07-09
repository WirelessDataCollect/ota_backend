package com.ruili.fota.constant;

import java.io.Serializable;

/**
 * enum class for device burn-flash status storage
 */
public enum LoadStatusEnum implements Serializable {

    /********** 下载相关的 **********/
    /**
     * 下发成功
     */
    LOAD_SUCCESS("下载成功", 66),
    /**
     * 下发失败
     */
    LOAD_ERROR("下载失败", 23),
    /**
     * 设备无下载状态，初始状态
     */
    LOAD_NO_STATUS("设备无下载状态", 0),
    /**
     * 设备正在下载状态
     */
    LOAD_ON_STATUS("设备正在下载状态", 67),
    /**
     * 未找到设备固件
     */
    LOAD_FIRMWARE_NOT_FOUND("未找到设备固件", 401),
    /**
     * 下载固件时设备离线
     */
    LOAD_FIRMWARE_DEVICE_DISCONNECT("下载固件时设备离线", 402),
    /**
     * 下发配置指令时将设备添加到下发map中，若没有在map中，则报错
     */
    LOAD_FIRMWARE_DEFORE_CONFIG("下载固件前没有进行配置", 403),
    /**
     * 下载固件时设备无ACK应答
     */
    LOAD_FIRMWARE_DEVICE_NO_ACK("下载固件时设备无ACK应答", 404),

    /********** 配置相关的 **********/
    /**
     * 下发配置成功，等待上报配置结果
     */
    CONFIG_SUCCESS("下发配置成功，等待上报配置结果", 68),
    /**
     * 下发配置失败
     */
    CONFIG_ERROR("下发配置失败", 69),
    /**
     * 无配置状态，初始化的状态
     */
    CONFIG_NO_STATUS("无配置状态", 70),
    /**
     * 下发配置时设备掉线
     */
    CONFIG_DEVICE_DISCONNECT("下发配置时设备掉线", 71),

    /********** 设备烧录过程相关的 **********/
    /**
     * CAN发送失败
     */
    CAN_SEND_ERROR("CAN发送失败", 100),
    /**
     * 未收到CAN回复
     */
    CAN_NO_REPLY("未收到CAN回复", 101),
    /**
     * 接收到协议外错误帧
     */
    ERROR_FRAME_RECEIVED("接收到协议外错误帧", 102),
    /**
     * 解密失败
     */
    DECRYPT_ERROR("解密失败", 103),
    /**
     * 文件错误
     */
    FILE_WRONG("文件错误", 104),
    /**
     * 升级成功
     */
    UPDATE_SUCCESS("升级成功", 106),

    /**
     * code无法识别
     */
    NOT_FOUND_CODE("code无法识别", 707);

    final String status;
    final int code;

    LoadStatusEnum(String msg, int code) {
        this.status = msg;
        this.code = code;
    }

    public static LoadStatusEnum searchByCode(int code) {
        for (LoadStatusEnum statusEnum : LoadStatusEnum.values()) {
            if (statusEnum.code == code) { return statusEnum;}
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
