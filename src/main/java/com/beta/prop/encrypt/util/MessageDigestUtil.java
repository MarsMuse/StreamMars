package com.beta.prop.encrypt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @ClassName:  MessageDigestUtil   
 * @Description:(信息摘要工具类)   
 * @author: FireMonkey
 * @date:   2017年7月13日 上午9:10:58   
 *     
 * @Copyright: 2017 
 *
 */
public class MessageDigestUtil {
    
    /**
     * 默认的信息摘要算法
     */
    private  static  final  String  DEFAULT_DIGEST_ALGORITHM  = "SHA-256";
    /**
     * 默认字符集
     */
    private  static  final  String  DEFAULT_CHAR_SET = "UTF-8";
    
    /**
     * 
     * @Title: digest   
     * @Description:(对数据进行摘要)   
     * @param: @param data
     * @param: @param algorithm
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    public  static  byte[]  digest(byte[]  data  ,  String  algorithm){
        byte[]  result  =   null;
        
        MessageDigest  md = null;
        try {
            md  =  MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        result  =  md.digest(data);
        
        return  result;
    }
    
    /**
     * 
     * @Title: digestToHexString   
     * @Description: (摘要数组转化为16进制字符串)   
     * @param: @param data
     * @param: @param algorithm
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  digestToHexString(byte[]  data  ,  String  algorithm){
        
        String  result  =  null;
        
        byte[]  digestArray  =  digest(data, algorithm);
        
        result  =  SecurityAlgorithmUtil.byteArrayConvertToHexString(digestArray);
        
        return  result;
    }
    /**
     * 
     * @Title: digestToHexString   
     * @Description:(获取摘要信息转化为16进制字符串)   
     * @param: @param data
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  digestToHexString(byte[]  data){
        
        return  digestToHexString(data, DEFAULT_DIGEST_ALGORITHM);
    }
    
    /**
     * 
     * @Title: digestToHexString   
     * @Description: (获取字符串摘要信息转化为16进制字符串)   
     * @param: @param sourceData
     * @param: @param algorithm
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static   String  digestToHexString(String  sourceData  ,  String  algorithm){
        
        byte[] data  =  null;
        try {
            data = sourceData.getBytes(DEFAULT_CHAR_SET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        return  digestToHexString(data , algorithm);
    }
    
    /**
     * 
     * @Title: digestToHexString   
     * @Description: (获取字符串摘要信息转化为16进制字符串)   
     * @param: @param sourceData
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static   String  digestToHexString(String  sourceData){
        return digestToHexString(sourceData  ,  DEFAULT_DIGEST_ALGORITHM);
    }
    
    /**
     * 
     * @Title: digestToHexString   
     * @Description:(对流进行信息摘要)   
     * @param: @param input
     * @param: @param algorithm
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  digestToHexString(InputStream  input,  String  algorithm){
        String  result  =  null;
        
        byte[]  digestArray  =  null;
        //算法引擎
        MessageDigest  md  =  null;
        //包装类
        DigestInputStream  dis  =  null;
        //4K
        byte[]  temp  =  new  byte[4096];
        try {
            //获取加密算法引擎
            md  =  MessageDigest.getInstance(algorithm);
            //获取流处理对象
            dis  =  new  DigestInputStream(input  ,  md);
            //读取数据
            while(dis.read(temp) >0);
            
            digestArray  =  md.digest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }finally{
            if(dis != null){
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //转化为64位16进制字符串
        result  =   SecurityAlgorithmUtil.byteArrayConvertToHexString(digestArray);
        return  result;
    }
    
    /**
     * 
     * @Title: digestToHexString   
     * @Description: (对流进行信息摘要获取)   
     * @param: @param input
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  digestToHexString(InputStream  input){
        
        return digestToHexString(input  ,  DEFAULT_DIGEST_ALGORITHM);
    }
    
    /**
     * 
     * @Title: digestToHexString   
     * @Description: (对文件进行信息摘要)   
     * @param: @param file
     * @param: @param algorithm
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  digestToHexString(File  file  ,  String  algorithm){
        String  result  =  null;
        
        InputStream  input  =  null;
        try {
            input  =  new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //获取流信息摘要
        result  =  digestToHexString(input  ,  algorithm);
        return  result;
    }
    
    /**
     * 
     * @Title: digestToHexString   
     * @Description: (对文件进行信息摘要)   
     * @param: @param file
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  digestToHexString(File  file){
        
        return  digestToHexString(file  ,  DEFAULT_DIGEST_ALGORITHM);
    }
    
}
