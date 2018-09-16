package com.baizhi.util;

import com.baizhi.entity.Emp;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/13 0013.
 * By 卢岩
 */
public class RandomUtil {
    public static String getRandoms(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random random=new Random();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int i1 = random.nextInt(chars.length);//随机生成chars数组的一个下标
            stringBuilder.append(chars[i1]);
        }
        return stringBuilder.toString();
    }
}
