package com.beta.api.entity;



/**
 *
 * Author: Zou Yao
 * Description: (推送批量扣款信息实体)
 * Time: 2017/7/21 11:21
 *
**/
public class PushBatchChargeInfo {

    //平台批扣对象
    private String workBatchNo;
    //扣款号
    private String chargeNo;
    //合同号
    private String loanNo;
    //扣款时间
    private String chargeTime;
    //扣款返回信息
    private String chargeMessage;
    //扣款状态
    private String chargeStatus;

    public String getWorkBatchNo() {
        return workBatchNo;
    }

    public void setWorkBatchNo(String workBatchNo) {
        this.workBatchNo = workBatchNo;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getChargeMessage() {
        return chargeMessage;
    }

    public void setChargeMessage(String chargeMessage) {
        this.chargeMessage = chargeMessage;
    }

    public String getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(String chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public PushBatchChargeInfo() {
    }

    public PushBatchChargeInfo(String workBatchNo, String loanNo, String chargeTime, String chargeMessage, String chargeStatus) {
        this.workBatchNo = workBatchNo;
        this.loanNo = loanNo;
        this.chargeTime = chargeTime;
        this.chargeMessage = chargeMessage;
        this.chargeStatus = chargeStatus;
    }
}
