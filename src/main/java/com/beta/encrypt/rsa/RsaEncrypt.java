package com.beta.encrypt.rsa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.beta.encrypt.util.SecurityAlgorithmUtil;

/**
 * 
 * @ClassName:  RsaEncrypt   
 * @Description:(RSA加密工具类)   
 * @author: FireMonkey
 * @date:   2017年7月10日 上午11:32:37   
 *     
 * @Copyright: 2017 
 *
 */
public class RsaEncrypt {
    
    
    
    /**
     * 
     * @Title: getBlockByteArray   
     * @Description: (截取字节数组)   
     * @param: @param sourceArray
     * @param: @param startIndex
     * @param: @param endIndex
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    private static byte[]  getBlockByteArray(byte[]  sourceArray , int startIndex , int endIndex){
        if(sourceArray == null  ||endIndex<0 || endIndex<startIndex){
            return null;
        }
        if(startIndex<0){
            startIndex = 0;
        }
        if(endIndex >sourceArray.length){
            endIndex  = sourceArray.length;
        }
        int blockedArraySize = endIndex-startIndex;
        
        byte[] result = new byte[blockedArraySize];
        
        System.arraycopy(sourceArray, startIndex, result, 0, blockedArraySize);
        return result;
    }
    
    /**
     * 
     * @Title: concatByteArray   
     * @Description: (合并数组)   
     * @param: @param sourceArray
     * @param: @param targetArray
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    private static byte[]  concatByteArray(byte[]  sourceArray , byte[] targetArray){
        if( sourceArray == null && targetArray ==null){
            return null;
        }else if(sourceArray == null || sourceArray.length ==0){
            return targetArray;
        }else if(targetArray == null ){
            return  sourceArray.clone();
        }
        //合并两个数组
        byte[] result = new byte[sourceArray.length + targetArray.length];
        System.arraycopy(targetArray, 0, result, 0, targetArray.length);
        System.arraycopy(sourceArray, 0, result, targetArray.length , sourceArray.length);
        return result;
    }
    /**
     * 
     * @Title: getPublicKeySize   
     * @Description: (获取公钥长度)   
     * @param: @param key
     * @param: @return      
     * @return: int      
     * @throws
     */
    private  static  int  getPublicKeySize(PublicKey  key){
        int length = 0;
        String  algorithm = key.getAlgorithm();
        KeyFactory keyFactory  =  null;
        try {
            keyFactory =  KeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        RSAPublicKeySpec  keySpec = null;
        try {
            keySpec = keyFactory.getKeySpec(key, RSAPublicKeySpec.class);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        BigInteger  prim =  keySpec.getModulus();
        length = prim.toString(2).length()/8;
        
        return length;
    }
    
    /**
     * 
     * @Title: getPrivateKeySize   
     * @Description: (获取私钥长度)   
     * @param: @param key
     * @param: @return      
     * @return: int      
     * @throws
     */
    private  static  int  getPrivateKeySize(PrivateKey  key){
        int length = 0;
        String  algorithm = key.getAlgorithm();
        KeyFactory keyFactory  =  null;
        try {
            keyFactory =  KeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        RSAPrivateKeySpec  keySpec = null;
        try {
            keySpec = keyFactory.getKeySpec(key, RSAPrivateKeySpec.class);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        BigInteger  prim =  keySpec.getModulus();
        length = prim.toString(2).length()/8;
        
        return length;
    }
    /**
     * 
     * @Title: encryptByPrivateKey   
     * @Description: (通过密钥加密数据)   
     * @param: @param sourceData
     * @param: @param privateKey
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    public static  byte[]  encryptByPrivateKey(byte[]  sourceData  ,  PrivateKey  privateKey){
        byte[]  result = null;
        
        try {
            Cipher  cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            int blockSize = getPrivateKeySize(privateKey) -11;
            for(int i = 0 ; i<sourceData.length ; i+=blockSize){
                byte[] temp = cipher.doFinal(getBlockByteArray(sourceData, i, i+blockSize));
                result = concatByteArray(temp, result);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("获取加密对象出现异常");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("校验秘钥出现异常");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new RuntimeException("加密数据出现异常");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("加密数据出现异常");
        }
        return result;
    }
    
    /**
     * 
     * @Title: decryptByPublicKey   
     * @Description: (通过公钥解密数据)   
     * @param: @param sourceData
     * @param: @param publicKey
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    public  static  byte[]  decryptByPublicKey(byte[]  sourceData  ,  PublicKey  publicKey){
        byte[]  result  =  null;
        try {
            Cipher  cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            int blockSize = getPublicKeySize(publicKey);
            for(int i = 0 ; i<sourceData.length ; i+=blockSize){
                byte[] temp = cipher.doFinal(getBlockByteArray(sourceData, i, i+blockSize));
                result = concatByteArray(temp, result);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("获取加密对象出现异常");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("校验秘钥出现异常");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new RuntimeException("加密数据出现异常");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("加密数据出现异常");
        }
        return result;
    }
    /**
     * 
     * @Title: encryptByPublicKey   
     * @Description: (公钥加密数据--对应私钥解密数据)   
     * @param: @param sourceData
     * @param: @param publicKey
     * @param: @return      
     * @return: byte[]      
     * @throws
     */
    public  static  byte[]  encryptByPublicKey(byte[]  sourceData  ,  PublicKey  publicKey){
        byte[]  result  = null;
        try {
            Cipher  cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            int blockSize = getPublicKeySize(publicKey) -11;
            for(int i = 0 ; i<sourceData.length ; i+=blockSize){
                byte[] temp = cipher.doFinal(getBlockByteArray(sourceData, i, i+blockSize));
                result = concatByteArray(temp, result);
            }
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
    
    public  static  byte[]  decryptByPrivateKey(byte[]  sourceData  ,  PrivateKey  privateKey){
        byte[]  result = null;
        
        try {
            Cipher  cipher  = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int blockSize = getPrivateKeySize(privateKey);
            for(int i = 0 ; i<sourceData.length ; i+=blockSize){
                byte[] temp = cipher.doFinal(getBlockByteArray(sourceData, i, i+blockSize));
                result = concatByteArray(temp, result);
            }
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
     * @Description: (加密数据)   
     * @param: @param sourceData
     * @param: @param privateKey
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static String  encryptData(String  sourceData , PrivateKey privateKey){
        String  result = null;
        byte[] data = null;
        try {
            data = encryptByPrivateKey(sourceData.getBytes(RsaConstant.CHAR_SET_DEF) , privateKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("字符集出现异常");
        }
        result  =  SecurityAlgorithmUtil.byteArrayConvertToHexString(data);
        return result;
    }
    
    /**
     * 
     * @Title: encryptData   
     * @Description: (从本地密钥库获取密钥加密数据)   
     * @param: @param sourceData
     * @param: @param keyStorePath
     * @param: @param alias
     * @param: @param password
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static String  encryptData(String  sourceData ,String  keyStorePath , String alias ,  String password){
        String  result = null;
        PrivateKey  privateKey  =  RsaKeyUtil.getPrivateKey(keyStorePath, alias, password);
        result = encryptData(sourceData , privateKey);
        return result;
    }
    
    /**
     * 
     * @Title: encryptData   
     * @Description: (公钥加密数据)   
     * @param: @param sourceData
     * @param: @param publicKey
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  encryptData(String  sourceData , PublicKey  publicKey){
        String  result = null;
        byte[] data = null;
        try {
            data = encryptByPublicKey(sourceData.getBytes(RsaConstant.CHAR_SET_DEF) , publicKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("字符集出现异常");
        }
        result  =  SecurityAlgorithmUtil.byteArrayConvertToHexString(data);
        return result;
    }
    /**
     * 
     * @Title: encryptData   
     * @Description: (通过获取证书加密数据)   
     * @param: @param sourceData
     * @param: @param certificateFilePath
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static String  encryptData(String sourceData  ,  String  certificateFilePath){
        String  result  = null;
        PublicKey  publicKey  =  RsaKeyUtil.getPublicKey(certificateFilePath);
        result  =  encryptData(sourceData  ,  publicKey);
        return result;
    }
    
    
    /**
     * 
     * @Title: decryptData   
     * @Description: (解密数据)   
     * @param: @param sourceData
     * @param: @param publicKey
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static String  decryptData(String sourceData  ,  PublicKey  publicKey){
        String  result = null;
        byte[]  data  =  decryptByPublicKey(SecurityAlgorithmUtil.hexStringConvertToByteArray(sourceData), publicKey);
        try {
            result  =  new String(data , RsaConstant.CHAR_SET_DEF);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("字符集出现异常");
        }
        return result;
    }
    /**
     * 
     * @Title: decryptData   
     * @Description: (解密数据)   
     * @param: @param sourceData
     * @param: @param certificateFilePath
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static String  decryptData(String sourceData  ,  String  certificateFilePath){
        String  result  = null;
        PublicKey  publicKey  =  RsaKeyUtil.getPublicKey(certificateFilePath);
        result  =  decryptData(sourceData  ,  publicKey);
        return result;
    }
    
    /**
     * 
     * @Title: decryptData   
     * @Description: (私钥加密数据)   
     * @param: @param sourceData
     * @param: @param privateKey
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static String  decryptData(String sourceData  ,  PrivateKey  privateKey){
        String  result = null;
        byte[]  data  =  decryptByPrivateKey(SecurityAlgorithmUtil.hexStringConvertToByteArray(sourceData), privateKey);
        try {
            result  =  new String(data , RsaConstant.CHAR_SET_DEF);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("字符集出现异常");
        }
        return result;
    }
    
    /**
     * 
     * @Title: decryptData   
     * @Description: (通过本地私钥解密数据)   
     * @param: @param sourceData
     * @param: @param keyStorePath
     * @param: @param alias
     * @param: @param password
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static String  decryptData(String  sourceData ,String  keyStorePath , String alias ,  String password){
        String  result = null;
        PrivateKey  privateKey  =  RsaKeyUtil.getPrivateKey(keyStorePath, alias, password);
        result = decryptData(sourceData , privateKey);
        return result;
    }
}
