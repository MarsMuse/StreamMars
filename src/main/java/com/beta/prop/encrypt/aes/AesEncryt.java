package com.beta.prop.encrypt.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.beta.prop.encrypt.util.SecurityAlgorithmUtil;


/**
 * 
 * @ClassName:  AesEncryt   
 * @Description:(AES加密/解密工具类)   
 * @author: FireMonkey
 * @date:   2017年7月12日 上午10:28:09   
 *     
 * @Copyright: 2017 
 *
 */
public class AesEncryt {
    
    
    private  AesEncryt(){
        throw  new RuntimeException("该类不可存在对象");
    }
    /**
     * 
     * @Title: encryptData   
     * @Description: (AES加密)   
     * @param: @param sourceData
     * @param: @param key
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    public  static byte[]  encryptData(byte[]  sourceData  ,  Key  key){
        byte[]  result  =  null;
        Cipher  cipher  =  null;
        //获取到算法实例
        try {
            cipher  =  Cipher.getInstance(AesConstant.CIPHER_ALGORITHM);
            //初始化算法
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //加密
            result  =  cipher.doFinal(sourceData);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return  result;
    }
    
    /**
     * 
     * @Title: encryptData   
     * @Description: (AES加密)   
     * @param: @param sourceData
     * @param: @param key
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  encryptData(String  sourceData  ,  Key  key){
        String result  =  null;
        byte[]  sourceArray  =  null;
        try {
            sourceArray  =  sourceData.getBytes(AesConstant.CHAR_SET_DEF);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        byte[] temp  =  encryptData(sourceArray , key);
        //转换为hex字符串 方便传输
        result  =  SecurityAlgorithmUtil.byteArrayConvertToHexString(temp);
        return result;
    }
    
    /**
     * 
     * @Title: decryptData   
     * @Description: (解密算法)   
     * @param: @param sourceData
     * @param: @param key
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    public  static  byte[]  decryptData(byte[]  sourceData  ,  Key  key){
        byte[]  result  =  null;
        Cipher  cipher  =  null;
        try {
            cipher  =  Cipher.getInstance(AesConstant.CIPHER_ALGORITHM);
            //初始化解密算法
            cipher.init(Cipher.DECRYPT_MODE, key);
            //解密
            result  =  cipher.doFinal(sourceData);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 
     * @Title: decryptData   
     * @Description: (解密算法)   
     * @param: @param sourceData
     * @param: @param key
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  decryptData(String  sourceData  ,  Key  key){
        String  result  = null;
        //转换成密文字节数组
        byte[]  sourceArray  =  SecurityAlgorithmUtil.hexStringConvertToByteArray(sourceData);
        //转换为明文字节数组
        byte[]  temp  =  decryptData(sourceArray  ,  key);
        
        try {
            result  =  new String(temp  ,  AesConstant.CHAR_SET_DEF);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        return  result;
    }
}
