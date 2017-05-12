package com.beta.app.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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
        //HttpPut httpPut = new HttpPut("http://192.168.7.249:8080/zhph_loanActivity/process/testSimpleProcess");
        HttpPost  httpPost  = new HttpPost("http://192.168.7.249:8080/zhph_loanActivity/process/task/testProcessApprovalId/262956");
        
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("result"  ,  "success"));  
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        CloseableHttpResponse response = null;
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
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
