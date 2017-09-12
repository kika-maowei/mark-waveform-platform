package com.kikakeyboard.waveform.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BeanUtil {

    public static String string2Md5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] cipherData = md5.digest(str.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte cipher : cipherData) {
            String toHexStr = Integer.toHexString(cipher & 0xff);
            builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
        }
        return builder.toString();
    }
}
