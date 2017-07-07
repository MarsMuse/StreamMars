package com.beta.app.utils;

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
    private SecurityAlgorithmUtil(){new RuntimeException("该类不可以创建对象");}
    
    
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
            new RuntimeException("提供的编码不存在，出现编码异常");
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
            new RuntimeException("提供的编码不存在，出现编码异常");
        }
        return result;
    }
    
    /**
     * 
     * @Title: parseBase64Binary   
     * @Description: (字符串转换成byteArray)   
     * @param: @param content
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    public static  byte[]  parseBase64Binary(String content){
        return  DatatypeConverter.parseBase64Binary(content);
    }
    
    /**
     * 
     * @Title: printBase64Binary   
     * @Description: (byteArray转换成字符串)   
     * @param: @param byteArray
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static  String  printBase64Binary(byte[] byteArray){
        
        return  DatatypeConverter.printBase64Binary(byteArray);
    }
}
