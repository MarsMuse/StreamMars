package com.beta.prop.encrypt.entity;

/**
 * Author: zou yao .
 * Created time: 2017/7/17 15.
 * Since: 1.0
 **/
public class InformationSecurityEntity {

    //AES加密算法密钥（用于解密）
    private  String encryptKey;

    //密文数据
    private  String cipherData;

    //平台签名（用于校验数据完整性，由平台私钥加密。）
    private  String platformSignature;

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public String getCipherData() {
        return cipherData;
    }

    public void setCipherData(String cipherData) {
        this.cipherData = cipherData;
    }

    public String getPlatformSignature() {
        return platformSignature;
    }

    public void setPlatformSignature(String platformSignature) {
        this.platformSignature = platformSignature;
    }

    public InformationSecurityEntity(String encryptKey, String cipherData, String platformSignature) {
        this.encryptKey = encryptKey;
        this.cipherData = cipherData;
        this.platformSignature = platformSignature;
    }

    public InformationSecurityEntity() {
    }

    @Override
    public String toString() {
        return "InformationSecurityEntity{" +
                "encryptKey='" + encryptKey + '\'' +
                ", cipherData='" + cipherData + '\'' +
                ", platformSignature='" + platformSignature + '\'' +
                '}';
    }
}
