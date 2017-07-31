package com.beta.prop.encrypt.rsa;

import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

/**
 * 
 * @ClassName:  RsaKeyUtil   
 * @Description:(获取到RSA秘钥工具)   
 * @author: FireMonkey
 * @date:   2017年7月10日 上午10:53:30   
 *     
 * @Copyright: 2017 
 *
 */
public class RsaKeyUtil {
    
    /**
     * 
     * @Title: getPrivateKey   
     * @Description: (在本地密钥库获取到私钥)   
     * @param: @param keyStorePath
     * @param: @param alias
     * @param: @param password
     * @param: @return      
     * @return: PrivateKey      
     * @throws
     */
    public  static  PrivateKey  getPrivateKey(String  keyStorePath , String alias ,  String password){
        PrivateKey  privateKey  =  null;
        FileInputStream  fis = null;
        try {
            fis = new FileInputStream(keyStorePath);
            //获取密钥库对象
            KeyStore  store =  KeyStore.getInstance(RsaConstant.KEY_STORE_TYPE_DEF);
            //加载密钥库对象
            store.load(fis, password.toCharArray());
            //获取到秘钥
            privateKey  =  (PrivateKey) store.getKey(alias, password.toCharArray());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("密钥库路径出现异常");
        } catch (KeyStoreException e) {
            e.printStackTrace();
            throw new RuntimeException("获取密钥库对象出现异常");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("密钥库无此加密算法异常");
        } catch (CertificateException e) {
            e.printStackTrace();
            throw new RuntimeException("数字证书出现异常");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO出现异常");
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("秘钥出现异常");
        }finally{
            //如果流开启 在使用结束后关闭 在JDK1.7级以后可以额使用try-resource替代
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("密钥库文件流关闭出现异常");
                }
            }
        }
        return  privateKey;
    }
    
    /**
     * 
     * @Title: getPrivateKey   
     * @Description:(根据证书路径获取到私钥)   
     * @param: @param certificatePath
     * @param: @param password
     * @param: @return      
     * @return: PrivateKey      
     * @throws
     */
    public  static  PrivateKey  getPrivateKey(String  pfxPath  ,  String  password){
    	PrivateKey  privateKey  =  null;
    	
        InputStream  keyInput  = null;

        try {
            keyInput  =  new FileInputStream(pfxPath);
            privateKey  =  getPrivateKey(keyInput ,password );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return  privateKey;
    }

    /**
     *
     * @Title: getPrivateKey
     * @Description:(根据证书路径获取到私钥)
     * @param: @param certificatePath
     * @param: @param password
     * @param: @return
     * @return: PrivateKey
     * @throws
     */
    public  static  PrivateKey  getPrivateKey(InputStream keyInput  ,  String  password){
        PrivateKey  privateKey  =  null;
        KeyStore  keyStore =  null;

        try {
            //密钥库
            keyStore = KeyStore.getInstance(RsaConstant.KEY_STORE_BY_PRIVATE);
            //密码字符数组
            char[]  passwordArray  =  password.toCharArray();
            keyStore.load(keyInput , passwordArray);

            Enumeration aliasEnum  =  keyStore.aliases();
            String  alias  =  null;
            if(aliasEnum.hasMoreElements()){
                alias = (String) aliasEnum.nextElement();
            }
            privateKey  = (PrivateKey) keyStore.getKey(alias , passwordArray);

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        return  privateKey;

    }
    
    /**
     * 
     * @Title: getPublicKey   
     * @Description:(从证书中获取公钥)   
     * @param: @param certificatePath
     * @param: @return      
     * @return: PublicKey      
     * @throws
     */
    public  static  PublicKey  getPublicKey(String  certificatePath){
        PublicKey  publicKey  = null;
        
        CertificateFactory  certificateFactory ;
        
        FileInputStream  fis = null;
        try {
            certificateFactory =  CertificateFactory.getInstance(RsaConstant.CERTIFICATE_TYPE_DEF);
            
            fis  =  new FileInputStream(certificatePath);
            //获取到证书
            Certificate certificate = certificateFactory.generateCertificate(fis);
            
            publicKey  =  certificate.getPublicKey();
        } catch (CertificateException e) {
            e.printStackTrace();
            throw new RuntimeException("证书类型出现异常");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("证书文件找不到出现异常");
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("证书文件流关闭出现异常");
                }
            }
        }
        return publicKey;
    }
}
