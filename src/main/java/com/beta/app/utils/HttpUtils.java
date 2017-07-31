package com.beta.app.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.beta.prop.ChargeDetailInfo;
import com.beta.prop.encrypt.entity.InformationSecurityEntity;
import com.beta.prop.encrypt.entity.TransInformation;
import com.beta.prop.encrypt.util.InformationSecurityUtil;

/**
 * 
 * @ClassName:  HttpUtils   
 * @Description:(调用Apache 的http组件实现在服务器模拟浏览器发起http请求 ，并且获取到响应)   
 * @author: mars
 * @date:   2017年4月28日 下午5:40:36   
 *     
 * @Copyright: 2017 
 *
 */
public class HttpUtils {
    public static void main(String[] args) {
        CloseableHttpClient httpClient= HttpClients.createDefault(); 
        String mm = "http://127.0.0.1:8008/StreamMars/ws/api/batchData/batchCharge";
        String url = "http://127.0.0.1:8080/payment/ws/v1/chargeDealService/batchCharge";
        url  = "http://127.0.0.1:8080/payment/ws/v1/chargeDealService/batchUpdate";
        url = "http://127.0.0.1:8008/StreamMars/ws/api/batchData/batchCharge";
        HttpPost  httpPost  = new HttpPost(url);

        List<ChargeDetailInfo>  inf = new ArrayList<ChargeDetailInfo>();
        
        for(int i = 0 ; i< 901 ; i++){
        	ChargeDetailInfo te = new ChargeDetailInfo("200149007842181350", "客户"+i, "511521199510121564", "13889946733", "2017-07-19", "11", "1", "6230580000123706827", "2"+i, "测试人员"+i, System.currentTimeMillis()+"", "ZH", "成都总部销售中心");
        	inf.add(te);
        }
        
        for(int i = 0 ; i< 0 ; i++){
        	ChargeDetailInfo te = new ChargeDetailInfo("200149007842181350", "客户"+i, "", "13889146832", "2017-07-19", "11", "2", "", "5213"+i, "测试人员"+i, System.currentTimeMillis()+"", "ZH", "成都总部销售中心");
        	inf.add(te);
        }
        for(int i = 0 ; i< 0 ; i++){
        	ChargeDetailInfo te = new ChargeDetailInfo("   ", "客户"+i, "511521199510121564", "13889146832", "2017-07-19", "11", "2", "6230580000123706827", "5213"+i, "测试人员"+i, System.currentTimeMillis()+"", "ZH", "成都总部销售中心");
        	inf.add(te);
        }
        
        String info = JSON.toJSONString(inf);
        info = "1500974510207";
        //设置header信息  
        //指定报文头【Content-type】、【User-Agent】  
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");  
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        
        String pri="E:/testKey/dev/payment_dev_pri.pfx"; 
        String password= "abc123456";
        String pub = "E:/testKey/payment/payment_pub.cer";
        
        pub="E:/testKey/dev/payment_dev_pub.cer"; 
        password= "zhphpayment";
        pri = "E:/testKey/payment/payment_pri.pfx";
        
        InformationSecurityEntity ise = InformationSecurityUtil.encryptInformation(info, pub, pri, password);
        TransInformation ti = new TransInformation("CS007", "C001",ise.getEncryptKey(), ise.getCipherData(), ise.getPlatformSignature());
        String content = JSON.toJSONString(ti);
        httpPost.setEntity(new StringEntity(content,"UTF-8"));
        CloseableHttpResponse response = null;
        long start = System.currentTimeMillis();
        try {
            response = httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity httpEntity= response.getEntity();  
        try {
            String strResult= EntityUtils.toString(httpEntity);
            System.out.println(strResult);
            System.out.println("耗时："+( System.currentTimeMillis()-start)+"秒");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
