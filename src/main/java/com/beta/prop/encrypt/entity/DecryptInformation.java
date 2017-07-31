package com.beta.prop.encrypt.entity;

/**
 * Author: zou yao .
 * Created time: 2017/7/17 15.
 * Since: 1.0
 **/
public class DecryptInformation {

    //数据解密标志位（1：成功 2：失败）
    private  String code;

    //解密后数据
    private  String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DecryptInformation{" +
                "code='" + code + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
