package com.beta.prop.encrypt.entity;



public class TransInformation {

    //平台编码（数据库中有表维护）
    private String platformCode;

    //渠道编号
    private String channelNo;

    //AES加密算法密钥（用于解密）
    private  String encryptKey;

    //密文数据
    private  String cipherData;

    //平台签名（用于校验数据完整性，由平台私钥加密。）
    private  String platformSignature;

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

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

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public TransInformation(String platformCode, String channelNo, String encryptKey, String cipherData, String platformSignature) {
        this.platformCode = platformCode;
        this.channelNo = channelNo;
        this.encryptKey = encryptKey;
        this.cipherData = cipherData;
        this.platformSignature = platformSignature;
    }

    public TransInformation() {
    }
}
