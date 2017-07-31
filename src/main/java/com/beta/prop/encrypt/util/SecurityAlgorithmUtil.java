package com.beta.prop.encrypt.util;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

/**
 * 
 * @ClassName:  SecurityAlgorithmUtil   
 * @Description:(安全算法工具类)   
 * @author:FireMonkey
 * @date:   2017年7月7日 下午3:11:21   
 *     
 * @Copyright: 2017 
 *
 */
public final class SecurityAlgorithmUtil {
    
    /**
     * 默认字符集名称
     */
    private  static  final  String  DEFAULT_CHAR_SET = "UTF-8";
    /**
     * 
     * @Title:  SecurityAlgorithmUtil   
     * @Description:    (不允许创建对象)   
     * @param:    
     * @throws
     */
    private SecurityAlgorithmUtil(){throw new RuntimeException("该类不可以创建对象");}
    
    
    /**
     * 使用JDK1.6中的DatatypeConverter编解码数据，具有极高的性能，
     * 如果JDK版本升级到1.8则可以弃用方法，直接使用JDK1.8中java.util.Base64，性能更佳
     * @Title: base64Encode   
     * @Description: (通过Base64编码字符串,)   
     * @param: @param content
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static  String  base64Encode(String  content){
        
        return base64Encode(content ,  DEFAULT_CHAR_SET);
    }
    /**
     *  使用JDK1.6中的DatatypeConverter编解码数据，具有极高的性能，
     * 如果JDK版本升级到1.8则可以弃用方法，直接使用JDK1.8中java.util.Base64，性能更佳
     * @Title: base64Encode   
     * @Description: (通过Base64编码字符串)   
     * @param: @param content
     * @param: @param charset
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static String  base64Encode(String  content , String  charset){
        String  result = null;
        
        try {
            result  =  DatatypeConverter.printBase64Binary(content.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("提供的编码不存在，出现编码异常");
        }
        return result;
    }
    
    /**
     *  使用JDK1.6中的DatatypeConverter编解码数据，具有极高的性能，
     * 如果JDK版本升级到1.8则可以弃用方法，直接使用JDK1.8中java.util.Base64，性能更佳
     * @Title: base64Decode   
     * @Description: (通过Base64解码字符串)   
     * @param: @param content
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static  String  base64Decode(String  content){
        
        return base64Decode(content  ,  DEFAULT_CHAR_SET);
    }
    
    /**
     *  使用JDK1.6中的DatatypeConverter编解码数据，具有极高的性能，
     * 如果JDK版本升级到1.8则可以弃用方法，直接使用JDK1.8中java.util.Base64，性能更佳
     * @Title: base64Decode   
     * @Description: (通过Base64解码字符串)   
     * @param: @param content
     * @param: @param charset
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static  String  base64Decode(String  content , String  charset){
        String  result = null;
        try {
            result  =  new String (DatatypeConverter.parseBase64Binary(content) , charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("提供的编码不存在，出现编码异常");
        }
        return result;
    }
    
    /**
     * 
     * @Title: byteArrayConvertToHexString   
     * @Description: (byte数组转换成HEX字符串)   
     * @param: @param byteArray
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static  String  byteArrayConvertToHexString(byte[]  byteArray){
        if(byteArray == null || byteArray.length ==0){
            return null;
        }
        StringBuilder  stringBuilder = new StringBuilder();
        for(byte  info : byteArray){
            String  hexString = Integer.toHexString(0xFF & info);
            stringBuilder.append(hexString.length() == 1 ? Integer.valueOf(0):"").append(hexString);
        }
        return  stringBuilder.toString();
    }
    
    /**
     * 
     * @Title: hexStringConvertToByteArray   
     * @Description: (HEX字符串转换成为byte数组)   
     * @param: @param hexString
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    public  static byte[]  hexStringConvertToByteArray(String  hexString){
        byte[]  result  =  new byte[hexString.length()/2];
        for(int i = 0 ; i<result.length ; i++){
            result[i]  =  (byte) Integer.parseInt(hexString.substring(i*2, i*2+2), 16);
        }
        return result;
    }
    
}
