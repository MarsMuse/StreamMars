package com.beta.prop;



/**
 *
 * Author: Zou Yao
 * Description: (扣款详细信息实体)
 * Time: 2017/7/19 16:41
 *
**/
public class ChargeDetailInfo {


    //合同号
    private String loanNo;

    //客户姓名
    private String loanName;

    //客户身份证号
    private String loanIdCard;

    //客户手机号
    private String phoneNumber;

    //账单日
    private String paymentDate;

    //期数
    private String billTerm;

    //银行键
    private String bankKey;

    //银行卡号
    private String accountNumber;

    //金额
    private String amount;

    //操作人姓名
    private String operateName;

    //操作时间
    private String operateTime;

    //主体
    private String mainBody;

    //分公司名称
    private  String branchOrgName;

    //扣款编号
    private String chargeNo;


    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getLoanIdCard() {
        return loanIdCard;
    }

    public void setLoanIdCard(String loanIdCard) {
        this.loanIdCard = loanIdCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBillTerm() {
        return billTerm;
    }

    public void setBillTerm(String billTerm) {
        this.billTerm = billTerm;
    }

    public String getBankKey() {
        return bankKey;
    }

    public void setBankKey(String bankKey) {
        this.bankKey = bankKey;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getMainBody() {
        return mainBody;
    }

    public void setMainBody(String mainBody) {
        this.mainBody = mainBody;
    }


    public String getBranchOrgName() {
        return branchOrgName;
    }

    public void setBranchOrgName(String branchOrgName) {
        this.branchOrgName = branchOrgName;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public ChargeDetailInfo(String loanNo, String loanName, String loanIdCard, String phoneNumber, String paymentDate, String billTerm, String bankKey, String accountNumber, String amount, String operateName, String operateTime, String mainBody, String branchOrgName) {
        this.loanNo = loanNo;
        this.loanName = loanName;
        this.loanIdCard = loanIdCard;
        this.phoneNumber = phoneNumber;
        this.paymentDate = paymentDate;
        this.billTerm = billTerm;
        this.bankKey = bankKey;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.operateName = operateName;
        this.operateTime = operateTime;
        this.mainBody = mainBody;
        this.branchOrgName = branchOrgName;
    }

    public ChargeDetailInfo() {
    }



}
