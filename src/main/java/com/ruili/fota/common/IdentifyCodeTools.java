package com.ruili.fota.common;

/**
 * Created by Yin on 2019/1/2
 */
public class IdentifyCodeTools {

    /**
     * 生成六位数字验证码
     *
     * @return
     */
    public static String getRandom() {
        String num = "";
        for (int i = 0; i < 6; i++) {
            num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
        }
        return num;
    }

}
