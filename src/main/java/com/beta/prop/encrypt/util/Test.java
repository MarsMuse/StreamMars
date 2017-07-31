package com.beta.prop.encrypt.util;



import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.beta.prop.encrypt.entity.Aem;
import com.beta.prop.encrypt.entity.Cem;
import com.beta.prop.encrypt.entity.DecryptInformation;
import com.beta.prop.encrypt.entity.InformationSecurityEntity;
import com.beta.prop.encrypt.rsa.RsaKeyUtil;
import com.mchange.v2.lang.ObjectUtils;

/**
 * Author: zou yao .
 * Created time: 2017/7/17 14.
 * Since: 1.0
 **/
public class Test {


    public static void main(String[] args) {

        /*String  test  =  "aaa测试信息/*63548123";
        PrivateKey pk = RsaKeyUtil.getPrivateKey("E:/javaKey/payment_pri.pfx", "abc123456");
        PublicKey pub = RsaKeyUtil.getPublicKey("E:/javaKey/payment_pub.cer");*/
        /*String  encryt = RsaEncrypt.encryptData(test ,pk);
        System.out.println(encryt);
        String de = RsaEncrypt.decryptData(encryt , "D:\\IdeaSpace\\PaymentPlatform\\src\\main\\resources\\platform\\payment\\payment_pub.cer");
        System.out.println(test.equals(de));*/

        /*String  sign = SignatureUtil.signature(test , pk);
        System.out.println(sign);
        boolean bo = SignatureUtil.verify(test ,sign,pub );
        System.out.println(bo);*/

      /*  InformationSecurityEntity ise = InformationSecurityUtil.encryptInformation(test ,pub,pk );
        System.out.println(ise);
        DecryptInformation di = InformationSecurityUtil.decryptInformation(ise ,pub, pk );
        System.out.println(di);*/

        /*Key aes = AesKeyUtil.getSecurityKey();
        String enca = AesEncryt.encryptData(test ,aes );
        String ena = AesEncryt.decryptData(enca ,aes);
        System.out.println(test.equals(ena));*/
    	System.out.println(System.currentTimeMillis());
        System.out.println((System.currentTimeMillis()+"").substring(1, 12));
        
        List<Aem> a = new ArrayList<Aem>();
        
        for(int i = 0 ; i<1000 ; i++){
        	a.add(new Aem(i+"a", "andy", "2"));
        }
        
        String ac = JSON.toJSONString(a);
        System.out.println(ac.length());
        List<Cem> mm = JSON.parseArray(ac , Cem.class);
        System.out.println(mm.size());
        
    }
}
