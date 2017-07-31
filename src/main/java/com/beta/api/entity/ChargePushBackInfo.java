package com.beta.api.entity;



/**
 *
 * Author: Zou Yao
 * Description: (扣款信息推送返回信息)
 * Time: 2017/7/26 10:54
 *
**/
public class ChargePushBackInfo {


    private String chargeNo;//交易号

    private String status;//1、成功 2、失败

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChargePushBackInfo() {
    }

    public ChargePushBackInfo(String chargeNo, String status) {
        this.chargeNo = chargeNo;
        this.status = status;
    }
}
