package com.example.javawork.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 随机验证码生成
 * */
public class VerCodeGenerator {
    // 采样范围
    private static final String SYMBOLS = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();

    // 生成6位随机数
    public static String generateVerCode() {
        char[] number = new char[6];
        for(int i = 0; i < number.length; i++){
            number[i] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(number);
    }
}
