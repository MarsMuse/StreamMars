package com.beta.app.register;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSON;
import com.beta.app.http.HttpPoolUtil;

/**
 * 
 * @ClassName:  RegisterUtil   
 * @Description:(注册工具)   
 * @author: 
 * @date:   2017年7月31日 上午9:41:03   
 *     
 * @Copyright: 2017 
 *
 */
public class RegisterUtil {
    
    /**
     * 
     * @Title: getAreaCode   
     * @Description: TODO(获取到区域编码)   
     * @param: @param areaName
     * @param: @return      
     * @return: String      
     * @throws
     */
    public String getAreaCode(String  areaName){
        String result =null;
        String content = null;
        try {
            content = HttpPoolUtil.httpPost("http://www.cdhjfu.chengdu.gov.cn/queryHouseholdRegisterPlace.action");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<AreaInfoByChengdu> areaList = JSON.parseArray(content, AreaInfoByChengdu.class);
        for(AreaInfoByChengdu info : areaList){
            if(areaName.equals(info.getName())){
                result = info.getModuleNo();
                break;
            }
        }
        return result;
    }
    
    /**
     * 
     * @Title: getCenterCode   
     * @Description: TODO(获取到办证中心编码)   
     * @param: @param centerName
     * @param: @return      
     * @return: String      
     * @throws
     */
    public String getCenterCode(String  centerName){
         String result =null;
         String content = null;
         try {
             content = HttpPoolUtil.httpPost("http://www.cdhjfu.chengdu.gov.cn/queryChildByMultipleFirstNo.action"  , "firstNo","002,003");
         } catch (ClientProtocolException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         List<CenterInfo> centerList = JSON.parseArray(content, CenterInfo.class);
         for(CenterInfo info : centerList){
             if(centerName.equals(info.getName())){
                 result = info.getModuleNo();
                 break;
             }
         }
         return result;
    }
    
    public List<AppointmentDate> getReserveDate(String  centerCode){
        String content = null;
        try {
            content = HttpPoolUtil.httpPost("http://www.cdhjfu.chengdu.gov.cn/queryAppointmentDate.action"  , "dealDeptNo",centerCode);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<AppointmentDate> centerList = JSON.parseArray(content, AppointmentDate.class);
        return centerList;
   }
    
    public List<AppointmentPeriod>  getPeriod(String  centerCode){
        List<AppointmentPeriod> result = null;
        String content = null;
        try {
            content = HttpPoolUtil.httpPost("http://www.cdhjfu.chengdu.gov.cn/queryAppointmentPeriod.action"  , "dealDeptNo",centerCode);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = JSON.parseArray(content, AppointmentPeriod.class);
        return result;
    }
    
    
    public String  getResultContent(String url , Map<String , String>  formData){
    	String content = null;
        try {
            content = HttpPoolUtil.httpPost("http://www.cdhjfu.chengdu.gov.cn/saveAppointment.action"  , formData);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return content;
    }
    
    
    
}
