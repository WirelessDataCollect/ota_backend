package com.ruili.fota.common;

import java.util.UUID;

/**
* @author: liangjingxiong
* @date: 2019-04-09
* @description:
 * UUID工具类，生成唯一的ID
*/
public class UUIDTools {

    /**
     * 生成32长度的唯一id
     * @return
     */
    public static String getUUID32() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }

    public static void main(String[] args)  {
        System.out.println(getUUID32());
    }
}
