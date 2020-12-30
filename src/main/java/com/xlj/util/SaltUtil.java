package com.xlj.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Random;

/**
 * @Author XuLeJun
 * @Date 2020/7/17 18:22
 */

public class SaltUtil {

    // 生成salt的静态方法
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890~!@#$%^&*()/*-+".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(4);
//        System.out.println(salt);
    }
}
