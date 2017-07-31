package com.beta.app.register;

public class AppointmentPeriod {
    private String appointmentAmount;
    private String appointmentPeriod;
    private String appointmentPeriodCode;
    private String centerNo;
    private String id;
    public String getAppointmentAmount() {
        return appointmentAmount;
    }
    public void setAppointmentAmount(String appointmentAmount) {
        this.appointmentAmount = appointmentAmount;
    }
    public String getAppointmentPeriod() {
        return appointmentPeriod;
    }
    public void setAppointmentPeriod(String appointmentPeriod) {
        this.appointmentPeriod = appointmentPeriod;
    }
    public String getAppointmentPeriodCode() {
        return appointmentPeriodCode;
    }
    public void setAppointmentPeriodCode(String appointmentPeriodCode) {
        this.appointmentPeriodCode = appointmentPeriodCode;
    }
    public String getCenterNo() {
        return centerNo;
    }
    public void setCenterNo(String centerNo) {
        this.centerNo = centerNo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "AppointmentPeriod [appointmentAmount=" + appointmentAmount + ", appointmentPeriod=" + appointmentPeriod
                + ", appointmentPeriodCode=" + appointmentPeriodCode + ", centerNo=" + centerNo + ", id=" + id + "]";
    }
    
    
}
