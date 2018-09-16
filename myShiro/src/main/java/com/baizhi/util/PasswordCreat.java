package com.baizhi.util;
import org.apache.shiro.crypto.hash.Md5Hash;


/**
 * Created by Administrator on 2018/9/13 0013.
 */
public class PasswordCreat {
    public static void main(String[] args) {
        System.out.println(getPassword());
    }
    public static String getPassword(){
        String randoms = RandomUtil.getRandoms();//私盐
        System.out.println(randoms);
        Md5Hash md5Hash=new Md5Hash("123456",randoms,1024);
        String s = md5Hash.toHex();//拿到加密之后的结果
        return s;
    }
}
