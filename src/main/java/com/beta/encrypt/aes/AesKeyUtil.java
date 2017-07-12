package com.beta.encrypt.aes;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.beta.encrypt.util.SecurityAlgorithmUtil;

/**
 * 
 * @ClassName:  AesKeyUtil   
 * @Description:(获取到安全密钥类工具类 此类为终态类，无对象)   
 * @author: FireMonkey
 * @date:   2017年7月12日 上午10:25:15   
 *     
 * @Copyright: 2017 
 *
 */
public class AesKeyUtil {
    
    private  AesKeyUtil(){
        throw new RuntimeException("此类为终态类 ，无需对象。");
    }

    /**
     * 
     * @Title: getSecurityKey   
     * @Description: (获取到AES安全密钥)   
     * @param: @param sourceKey
     * @param: @return      
     * @return: Key      
     * @throws
     */
    public  static  Key  getSecurityKey(String  sourceKey){
        Key  key = null;
        byte[]  sourceArray  =  sourceKey.getBytes();
        key = getSecurityKey(sourceArray);
        return  key;
    }
    
    /**
     * 
     * @Title: getSecurityKey   
     * @Description: (获取到安全密钥)   
     * @param: @param sourceArray
     * @param: @return      
     * @return: Key      
     * @throws
     */
    public  static  Key  getSecurityKey(byte[]  sourceArray){
        //新建一个安全密钥对象
        SecretKey secretKey  =  new  SecretKeySpec(sourceArray , AesConstant.KEY_ALGORITHM);
        
        return  secretKey;
    }
    
    /**
     * 
     * @Title: getSecurityKey   
     * @Description: (获取到安全密钥)   
     * @param: @param keySize
     * @param: @return      
     * @return: Key      
     * @throws
     */
    public  static  Key  getSecurityKey(int  keySize){
        
        if(keySize != 128 && keySize!= 192 && keySize!=256){
            throw new RuntimeException("密钥长度不符合规定，请选择128/192/256其中一个数字作为密钥长度。");
        }
        KeyGenerator  generator  = null;
        try {
            generator =  KeyGenerator.getInstance(AesConstant.KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        generator.init(keySize);
        
        SecretKey  secretKey  =  generator.generateKey();
        
        return  secretKey;
    }
    
    /**
     * 
     * @Title: getSecurityKey   
     * @Description: (获取到安全密钥)   
     * @param: @return      
     * @return: Key      
     * @throws
     */
    public  static  Key  getSecurityKey(){
        
        Key  key  = getSecurityKey(128);
        return key;
    }
    
    /**
     * 
     * @Title: keyConvertToString   
     * @Description:(密钥加密后转化为字符串进行传输)   
     * @param: @param key
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  keyConvertToString(Key  key){
        byte[] keyArray  =  key.getEncoded();
        return  SecurityAlgorithmUtil.byteArrayConvertToHexString(keyArray);
    }
    
    /**
     * 
     * @Title: stringConvertToKey   
     * @Description:(传输后的密钥字符串转换成密钥用于解密)   
     * @param: @param keyStr
     * @param: @return      
     * @return: Key      
     * @throws
     */
    public  static  Key  stringConvertToKey(String  keyStr){
        Key  key  = null;
        byte[]  keyArray  =   SecurityAlgorithmUtil.hexStringConvertToByteArray(keyStr);
        
        key  =  getSecurityKey(keyArray);
        return  key;
    }
    
}
