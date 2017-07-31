package com.beta.prop.encrypt.util;


import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 *
 * @Author: Zou Yao
 * @Description: (数字签名工具类，使用RSA经典数字签名)
 * @Time: 2017/7/17 14:37
 *
**/
public class SignatureUtil {


    //默认字符集
    private  static  final  String  DEF_CHAR_SET   = "UTF-8";

    //默认算法
    private  static  final  String  DEF_ALGORITHM  = "MD5withRSA";


    /**
     *
     * @Author: zou yao
     * @Description: {数字签名}
     * @Date: 2017/7/17 14:50
     * @Param:
     *
    **/
    public  static  byte[]  signature(byte[]  data , PrivateKey privateKey){

        byte[]  result = null;
        try {
            Signature  signature  =  Signature.getInstance(DEF_ALGORITHM);

            signature.initSign(privateKey);

            signature.update(data);

            result  = signature.sign();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return result;
    }



    /**
     *
     * @Author: zou yao
     * @Description: {数字签名}
     * @Date: 2017/7/17 14:50
     * @Param:
     *
    **/
    public  static  String  signature(String  sourceStr , PrivateKey privateKey){
        byte[] data = null;
        String result = null;
        try {
            data  =  sourceStr.getBytes(DEF_CHAR_SET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[]  signArray  =  signature(data , privateKey);
        result = SecurityAlgorithmUtil.byteArrayConvertToHexString(signArray);

        return result;
    }


    /**
     *
     * @Author: zou yao
     * @Description: {验证签名}
     * @Date: 2017/7/17 15:13
     * @Param: No such property: code for class: Script1
     *
    **/
    public  static  boolean verify(byte[] data , byte[] signatrueArray , PublicKey publicKey){
        boolean  result  =  false;

        try {
            Signature  signature  =  Signature.getInstance(DEF_ALGORITHM);
            signature.initVerify(publicKey);

            signature.update(data);

            result = signature.verify(signatrueArray);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     *
     * @Author: zou yao
     * @Description: {验证签名}
     * @Date: 2017/7/17 15:13
     * @Param: No such property: code for class: Script1
     *
    **/
    public  static  boolean  verify(String sourceData , String signatrueData , PublicKey publicKey){
        boolean  result = false;
        byte[] data = null;
        try {
            data  =  sourceData.getBytes(DEF_CHAR_SET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[]  signatureArray  =  SecurityAlgorithmUtil.hexStringConvertToByteArray(signatrueData);

        result =  verify(data , signatureArray , publicKey);
        return  result;
    }


}
