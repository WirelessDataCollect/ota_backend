package com.ruili.fota.common.utils;

import io.netty.buffer.ByteBuf;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Tools {

    public static String getMD5(ByteBuf byteBuf) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] buf = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(buf);
        return toHexString(md5.digest(buf));
    }

    private static String toHexString(byte[] b) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int x = b[i] & 0xff;
            if (x < 0x10) {
                s.append("0");
            }
            s.append(Integer.toHexString(x));
        }
        return s.toString();
    }
}
