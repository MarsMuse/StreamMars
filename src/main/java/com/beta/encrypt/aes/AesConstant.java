package com.beta.encrypt.aes;

/**
 * 
 * @ClassName:  AesConstant   
 * @Description:TODO(AES加密/解密常量)   
 * @author: zouyao
 * @date:   2017年7月12日 上午10:02:41   
 *     
 * @Copyright: 2017 
 *
 */
public class AesConstant {
    /**
     * 加密算法
     */
    public  static  final  String  KEY_ALGORITHM ="AES";
    
    /**
     *加密解密算法/工作模式/填充方式
     */
    public  static  final  String  CIPHER_ALGORITHM="AES/ECB/PKCS5Padding";
    
    /**
     * 默认字符集
     */
    public  static  final  String  CHAR_SET_DEF = "UTF-8";
}
