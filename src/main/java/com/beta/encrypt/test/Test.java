package com.beta.encrypt.test;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.beta.encrypt.aes.AesEncryt;
import com.beta.encrypt.aes.AesKeyUtil;
import com.beta.encrypt.rsa.RsaEncrypt;
import com.beta.encrypt.rsa.RsaKeyUtil;

public class Test {
    public static void main(String[] args) {
        /*String sou= "测试加密数据是否存在错误0123456789.*-+qweertyuiopasldfkgjhmzxncbv";
        char[]  da = sou.toCharArray();
        StringBuffer  sb =  new StringBuffer();
        Random ran = new Random();
        for(int i = 0 ; i<1000000; i++){
            sb.append(da[ran.nextInt(da.length)]);
            
        }
        String source = sb.toString();
        System.out.println(sb.length());
        long startTime = System.currentTimeMillis();
        String encryptInfo = RsaEncrypt.encryptData(source, "E:/javaKey/pay_pub_key.cer");
        System.out.println("加密时间："+(System.currentTimeMillis()-startTime));
        System.out.println(encryptInfo.length());
        long destartTime = System.currentTimeMillis();
        String decryptInfo = RsaEncrypt.decryptData(encryptInfo, "E:/javaKey/payment.keystore", "paymentkey", "abc123456");
        System.out.println("解密时间："+(System.currentTimeMillis()-destartTime));
        System.out.println(decryptInfo.equals(source));*/
        
        
        
/*        byte[]  mm = new  byte[]{1,2,3,4,5,6,7,8,9,0};
        Random ran1 = new Random();
        int len =  1000000;
        byte[] sour = new byte[len];
        for(int i = 0 ; i <len ; i++){
            sour[i] = mm[ran1.nextInt(mm.length)];
        }
        PublicKey publicKey = RsaKeyUtil.getPublicKey("E:/javaKey/pay_pub_key.cer");
        PrivateKey privateKey = RsaKeyUtil.getPrivateKey("E:/javaKey/payment.keystore", "paymentkey", "abc123456");
        long startTime1 = System.currentTimeMillis();
        byte[]  enByte = RsaEncrypt.encryptByPublicKey(sour, publicKey);
        System.out.println("加密时间："+(System.currentTimeMillis()-startTime1));
        System.out.println(enByte.length);
        long destartTime1 = System.currentTimeMillis();
        byte[]  deByte = RsaEncrypt.decryptByPrivateKey(enByte, privateKey);
        System.out.println("解密时间："+(System.currentTimeMillis()-destartTime1));*/
        
/*        int flag = 0;
        Provider[]  providers =  Security.getProviders();
        for(Provider info : providers){
            System.out.println(++flag+"提供者信息："+info+"开始");
            
            for(Map.Entry<Object, Object>  str:  info.entrySet()){
                System.out.println("键："+str.getKey());
            }
            
            System.out.println("***********************END******************************");
        }*/
        
/*        MessageDigest  md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for(byte a :md.digest()){
            
            String  hexString = Integer.toHexString( 0xFF &a);
            System.out.println((0xFF &a )+"--->"+a+"-->"+hexString+"-->");
            
            int temp = Integer.parseInt(hexString, 16);
            if(temp >-127 || temp<128){
                System.out.println((byte)temp);
            }
            //System.out.println(hexString);
        }*/
        
        
/*        String sou= "测试加密数据是否存在错误0123456789.*-+qweertyuiopasldfkgjhmzxncbv";
        char[]  da = sou.toCharArray();
        StringBuffer  sb =  new StringBuffer();
        Random ran = new Random();
        for(int i = 0 ; i<10000000; i++){
            sb.append(da[ran.nextInt(da.length)]);
            
        }
        String source = sb.toString();
        System.out.println(sb.length());
        Key  key  =  AesKeyUtil.getSecurityKey();
        String  keyStr  =  AesKeyUtil.keyConvertToString(key);
        long startTime = System.currentTimeMillis();
        //对称加密数据
        String  encryInfo =  AesEncryt.encryptData(source, key);
        //非对称加密密钥
        String  encryptKey  =  RsaEncrypt.encryptData(keyStr, "E:/javaKey/pay_pub_key.cer");
        System.out.println(System.currentTimeMillis() -startTime );
        long endTime = System.currentTimeMillis();
        String deKey  =  RsaEncrypt.decryptData(encryptKey, "E:/javaKey/payment.keystore", "paymentkey", "abc123456");
        Key tKey = AesKeyUtil.stringConvertToKey(deKey);
        String  deInfo  =  AesEncryt.decryptData(encryInfo, tKey);
        System.out.println(System.currentTimeMillis() -endTime );
        System.out.println(source.equals(deInfo));*/
        
/*        Executor exe = Executors.newFixedThreadPool(1000);
        for(int i  = 0 ;i<10000;  i++ ){
        	final  int temp = i;
            exe.execute(new Runnable() {
                @Override
                public void run() {
                    String sou= "测试加密数据是否存在错误0123456789.*-+qweertyuiopasldfkgjhmzxncbv";
                    char[]  da = sou.toCharArray();
                    StringBuffer  sb =  new StringBuffer();
                    Random ran = new Random();
                    for(int i = 0 ; i<2000000; i++){
                        sb.append(da[ran.nextInt(da.length)]);
                        
                    }
                    String source = sb.toString();
                    //System.out.println(sb.length());
                    Key  key  =  AesKeyUtil.getSecurityKey();
                    String  keyStr  =  AesKeyUtil.keyConvertToString(key);
                    //long startTime = System.currentTimeMillis();
                    //对称加密数据
                    String  encryInfo =  AesEncryt.encryptData(source, key);
                    //非对称加密密钥
                    String  encryptKey  =  RsaEncrypt.encryptData(keyStr, "E:/javaKey/pay_pub_key.cer");
                    //System.out.println(System.currentTimeMillis() -startTime );
                    //long endTime = System.currentTimeMillis();
                    String deKey  =  RsaEncrypt.decryptData(encryptKey, "E:/javaKey/payment.keystore", "paymentkey", "abc123456");
                    Key tKey = AesKeyUtil.stringConvertToKey(deKey);
                    String  deInfo  =  AesEncryt.decryptData(encryInfo, tKey);
                    //System.out.println(System.currentTimeMillis() -endTime );
                    System.out.println(temp+"线程结果："+source.equals(deInfo));
                    
                }
            });
        }*/
    	
    	
    	
    	String sou= "0123456789.*-+qweertyuiopasldfkgjhmzxncbv";
        char[]  da = sou.toCharArray();
        StringBuffer  sb =  new StringBuffer();
        Random ran = new Random();
        for(int i = 0 ; i<10000; i++){
            sb.append(da[ran.nextInt(da.length)]);
            
        }
        String source = sb.toString();
        try {
			System.out.println(sou.getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
