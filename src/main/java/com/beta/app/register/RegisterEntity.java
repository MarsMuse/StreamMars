package com.beta.app.register;

import java.util.HashMap;
import java.util.Map;

public class RegisterEntity {

    private String itemType;
    
    private String householdRegisterPlace;
    
    private String dealDeptNo;
    
    private String appointmentDate;
    
    private String appointmentPeriodCode;
    
    private String applicantName;
    
    private String idCard;
    
    private String mobile;
    
    private String isReceiveSms;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getHouseholdRegisterPlace() {
        return householdRegisterPlace;
    }

    public void setHouseholdRegisterPlace(String householdRegisterPlace) {
        this.householdRegisterPlace = householdRegisterPlace;
    }

    public String getDealDeptNo() {
        return dealDeptNo;
    }

    public void setDealDeptNo(String dealDeptNo) {
        this.dealDeptNo = dealDeptNo;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentPeriodCode() {
        return appointmentPeriodCode;
    }

    public void setAppointmentPeriodCode(String appointmentPeriodCode) {
        this.appointmentPeriodCode = appointmentPeriodCode;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIsReceiveSms() {
        return isReceiveSms;
    }

    public void setIsReceiveSms(String isReceiveSms) {
        this.isReceiveSms = isReceiveSms;
    }
    
    public  Map<String , String>  getFormData(){
        Map<String , String> result = new  HashMap<String , String>();
        result.put("appointment.itemType", itemType);
        result.put("appointment.householdRegisterPlace", householdRegisterPlace);
        result.put("appointment.dealDeptNo", dealDeptNo);
        result.put("appointment.appointmentDate", appointmentDate);
        result.put("appointment.appointmentPeriodCode", appointmentPeriodCode);
        result.put("appointment.applicantName", applicantName);
        result.put("appointment.idCard", idCard);
        result.put("appointment.mobile", mobile);
        result.put("appointment.isReceiveSms", isReceiveSms);
        return result;
    }

    public RegisterEntity(String itemType, String householdRegisterPlace, String dealDeptNo, String appointmentDate,
            String appointmentPeriodCode, String applicantName, String idCard, String mobile, String isReceiveSms) {
        super();
        this.itemType = itemType;
        this.householdRegisterPlace = householdRegisterPlace;
        this.dealDeptNo = dealDeptNo;
        this.appointmentDate = appointmentDate;
        this.appointmentPeriodCode = appointmentPeriodCode;
        this.applicantName = applicantName;
        this.idCard = idCard;
        this.mobile = mobile;
        this.isReceiveSms = isReceiveSms;
    }

    @Override
    public String toString() {
        return "RegisterEntity [itemType=" + itemType + ", householdRegisterPlace=" + householdRegisterPlace
                + ", dealDeptNo=" + dealDeptNo + ", appointmentDate=" + appointmentDate + ", appointmentPeriodCode="
                + appointmentPeriodCode + ", applicantName=" + applicantName + ", idCard=" + idCard + ", mobile="
                + mobile + ", isReceiveSms=" + isReceiveSms + "]";
    }
    
    
}
