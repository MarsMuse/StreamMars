package com.beta.encrypt.test;

import java.util.Random;

import com.beta.encrypt.rsa.RsaEncrypt;

public class Test {
    public static void main(String[] args) {
        String sou= "测试加密数据是否存在错误0123456789.*-+qweertyuiopasldfkgjhmzxncbv";
        char[]  da = sou.toCharArray();
        StringBuffer  sb =  new StringBuffer();
        Random ran = new Random();
        for(int i = 0 ; i<10000; i++){
        	sb.append(da[ran.nextInt(da.length)]);
        	
        }
        String source = sb.toString();
        System.out.println(sb.length());
        String encryptInfo = RsaEncrypt.encryptData(source, "E:/javaKey/payment.keystore", "paymentkey", "abc123456");
        System.out.println(encryptInfo.length());
        String decryptInfo = RsaEncrypt.decryptData(encryptInfo, "E:/javaKey/pay_pub_key.cer");
        System.out.println(decryptInfo.equals(source));
    }
}
