package com.sephinor.kanglong.kanglonguser.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Helper {

    /**
     * 生成随机数
     * @param length
     * @return
     */
    public static String randomNum(int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


    /**
     * 生成随机码
     * @param length
     * @return
     */
    public String randomCode(int length){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String n = "23456789aceghmnpqrt";
        StringBuilder sb =new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(n.charAt(random.nextInt(n.length())));
        }
        return sb.toString();
    }

}
