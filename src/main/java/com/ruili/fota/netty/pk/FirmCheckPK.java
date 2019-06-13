package com.ruili.fota.netty.pk;

/**
* @author: liangjingxiong
* @date: 2019-05-10
* @description:下发给设备固件文件校验包，没下载50k校验一次
*/
public class FirmCheckPK {
    private final String type = CommandType.FIRMWARE_CHECK.getType();
    private String md5;
    private int packNum;

    public FirmCheckPK(String md5, int packNum) {
        this.md5 = md5;
        this.packNum = packNum;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"md5\":\"")
                .append(md5).append('\"');
        sb.append(",\"packNum\":")
                .append(packNum);
        sb.append('}');
        return sb.toString();
    }
}
