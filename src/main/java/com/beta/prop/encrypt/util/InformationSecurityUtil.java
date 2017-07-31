package com.beta.prop.encrypt.util;




import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.beta.prop.encrypt.aes.AesEncryt;
import com.beta.prop.encrypt.aes.AesKeyUtil;
import com.beta.prop.encrypt.entity.DecryptInformation;
import com.beta.prop.encrypt.entity.InformationSecurityEntity;
import com.beta.prop.encrypt.rsa.RsaEncrypt;
import com.beta.prop.encrypt.rsa.RsaKeyUtil;

/**
 *
 * @Author: Zou Yao
 * @Description: (信息安全工具类)
 * @Time: 2017/7/17 15:19
 *
**/
public class InformationSecurityUtil {



    /**
     *
     * @Author: zou yao
     * @Description: {用户传入加密字符串，加密数据的公钥，用于签名的私钥对数据进行统一加密，返回一个安全信息对象}
     * @Date: 2017/7/17 15:23
     * @Param:
     *
    **/
    public static InformationSecurityEntity encryptInformation(String sourceData  , PublicKey encryptPubKey  , PrivateKey signatureKey){
        InformationSecurityEntity ise =  new InformationSecurityEntity();
        //获取到签名
        String  signInfor  =  SignatureUtil.signature(sourceData , signatureKey);
        ise.setPlatformSignature(signInfor);
        //获取到AES加密密钥
        Key aesKey = AesKeyUtil.getSecurityKey();
        //通过公钥加密AES密钥
        String encryptKey = RsaEncrypt.encryptData(AesKeyUtil.keyConvertToString(aesKey) , encryptPubKey);
        ise.setEncryptKey(encryptKey);
        //获取密文
        String  cipherData = AesEncryt.encryptData(sourceData , aesKey);
        ise.setCipherData(cipherData);

        return ise;
    }


    /**
     *
     * @Author: zou yao
     * @Description: {用户传入加密字符串，加密数据的公钥，用于签名的私钥对数据进行统一加密，返回一个安全信息对象}
     * @Date: 2017/7/17 15:52
     * @Param: No such property: code for class: Script1
     *
    **/
    public static InformationSecurityEntity encryptInformation(String sourceData , String  encryptKeyCerPath , String signatureKeyPfxPath ,String signatureKeyPassword){

        InformationSecurityEntity ise = null;
        //获取到加密数据公钥
        PublicKey encryptPubKey = RsaKeyUtil.getPublicKey(encryptKeyCerPath);
        //获取到签名私钥
        PrivateKey signatureKey = RsaKeyUtil.getPrivateKey(signatureKeyPfxPath , signatureKeyPassword);
        //加密数据
        ise  = encryptInformation(sourceData , encryptPubKey ,signatureKey);

        return ise;
    }

    /**
     *
     * @Author: zou yao
     * @Description: {用户传入信息安全对象，与验证公钥，解密私钥对数据进行解密}
     * @Date: 2017/7/17 15:40
     * @Param:
     *
    **/
    public static DecryptInformation  decryptInformation(InformationSecurityEntity ise , PublicKey verifyKey  , PrivateKey decryptKey){
        DecryptInformation  di =  new DecryptInformation();
        try {
            //使用私钥解密AES加密密钥
            String aesDecryptKey = RsaEncrypt.decryptData(ise.getEncryptKey(), decryptKey);
            //获取到AES密钥
            Key aesKey = AesKeyUtil.stringConvertToKey(aesDecryptKey);
            //获取到明文
            String data = AesEncryt.decryptData(ise.getCipherData(), aesKey);
            di.setData(data);
            //验证签名
            boolean signVerify = SignatureUtil.verify(data, ise.getPlatformSignature(), verifyKey);
            if(signVerify){
                di.setCode("1");
            }
            else {
                di.setCode("2");
            }

        }catch (Exception e){
            //设置解密数据异常
            di.setCode("2");
        }

        return di;
    }


    /**
     *
     * @Author: zou yao
     * @Description: {用户传入信息安全对象，与验证公钥，解密私钥对数据进行解密}
     * @Date: 2017/7/17 15:53
     * @Param: No such property: code for class: Script1
     *
    **/
    public static DecryptInformation  decryptInformation(InformationSecurityEntity ise ,String  verifyKeyCerPath , String  decryptKeyPfxPath , String  decryptKeyPassword){
        DecryptInformation di= null;

        //获取到加密数据公钥
        PublicKey verifyKey = RsaKeyUtil.getPublicKey(verifyKeyCerPath);
        //获取到签名私钥
        PrivateKey decryptKey = RsaKeyUtil.getPrivateKey(decryptKeyPfxPath , decryptKeyPassword);
        //解密数据
        di = decryptInformation(ise , verifyKey ,decryptKey );

        return di;

    }

}
