package com.ruili.fota.common.utils;

import io.netty.buffer.ByteBuf;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Tools {

    public static String getMD5(ByteBuf byteBuf) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        System.out.println(md5.getAlgorithm());
        byte[] buf = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(buf);
        String md5res = byte2hexString(md5.digest(buf));
        System.out.println(md5res);
        return md5res;
    }

    public static String byte2hexString(byte[] bys) {
        StringBuffer hexVal = new StringBuffer();
        int val = 0;
        for (int i = 0; i < bys.length; i++) {
            //将byte转化为int  如果byte是一个负数就必须要和16进制的0xff做一次与运算
            val = ((int) bys[i]) & 0xff;
            if (val < 16) {
                hexVal.append("0");
            }
            hexVal.append(Integer.toHexString(val));
        }
        return hexVal.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        System.out.println(md5.getProvider());
    }
}
