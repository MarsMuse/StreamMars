package com.beta.app.http;

import java.util.ArrayList;
import java.util.List;

import com.beta.app.register.AppointmentDate;
import com.beta.app.register.AppointmentPeriod;
import com.beta.app.register.RegisterEntity;
import com.beta.app.register.RegisterUtil;

public class Demo {
    
    
    public static void main(String[] args) {
        RegisterUtil ru = new RegisterUtil();
        String arraCode = ru.getAreaCode("天府新区");
        String centerCode = ru.getCenterCode("天府新区公安办证中心");
        List<AppointmentDate> dateList = ru.getReserveDate(centerCode);
        
        List<AppointmentPeriod> periodList =  ru.getPeriod(centerCode);
        
        List<RegisterEntity>  regieterList = new ArrayList<RegisterEntity>();
        
        for(AppointmentDate date : dateList){
            for(AppointmentPeriod period : periodList){
                RegisterEntity temp = new RegisterEntity("0", arraCode, centerCode, date.getAppointmentDate(), period.getAppointmentPeriodCode(), "测试人员", "511521199310121535", "13789196832", "1");
                regieterList.add(temp);
                System.out.println(temp);
            }
        }
    }
}
