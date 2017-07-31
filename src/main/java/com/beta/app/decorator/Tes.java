package com.beta.app.decorator;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import com.beta.prop.encrypt.util.SecurityAlgorithmUtil;





public class Tes {
    
    public static void main(String[] args) {
    	
        /*String  message = "测试信息假币00011---额12112312121";
        char[] bufer = message.toCharArray();
        int len  = bufer.length;
        Random  ran =  new Random();
        StringBuffer sb = new StringBuffer("开始");
        for(int a = 0 ; a< 1000 ; a++){
            sb.append(bufer[ran.nextInt(len-1)]);
        }
        String mm = sb.toString();
        String encdoe = SecurityAlgorithmUtil.base64Encode(mm);
        String encdoea = SecurityAlgorithmUtil.base64Encode(encdoe);
        System.out.println(encdoea);
        System.out.println(encdoe.equals(encdoea));
        System.out.println(encdoe.length());
        String  a = SecurityAlgorithmUtil.base64Decode(encdoe);
        System.out.println(a);*/
    	
    	System.out.println( String.valueOf(System.currentTimeMillis()).substring(1));
    }
    
    public static String  base64Encode(String  message) throws UnsupportedEncodingException{
        
        return  DatatypeConverter.printBase64Binary(message.getBytes("UTF-8"));
    }
    
    public  static String  base64Decode(String  message) throws UnsupportedEncodingException{
        
        return new String(DatatypeConverter.parseBase64Binary(message) , "UTF-8");
    }
}
