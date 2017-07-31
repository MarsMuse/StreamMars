package com.beta.prop.encrypt.rsa;

/**
 * 
 * @ClassName:  RsaConstant   
 * @Description:(RSA加密常量)   
 * @author: FireMonkey
 * @date:   2017年7月10日 上午10:49:57   
 *     
 * @Copyright: 2017 
 *
 */
public class RsaConstant {
    //默认字符集
    public final static String  CHAR_SET_DEF="UTF-8";
    //默认加密算法
    public final static String  RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    //keySotre类型
    public final static String  KEY_STORE_TYPE_DEF = "JKS";
    //证书类型
    public final static String  CERTIFICATE_TYPE_DEF = "X.509";
    //秘钥从文件获取keystore类型
    public final static String  KEY_STORE_BY_PRIVATE = "PKCS12";
}
