package com.beta.app.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @ClassName:  HttpUtils   
 * @Description:TODO(调用Apache 的http组件实现在服务器模拟浏览器发起http请求 ，并且获取到响应)   
 * @author: mars
 * @date:   2017年4月28日 下午5:40:36   
 *     
 * @Copyright: 2017 
 *
 */
public class HttpUtils {
    public static void main(String[] args) {
        CloseableHttpClient httpClient= HttpClients.createDefault();  
        HttpPut httpPut = new HttpPut("http://www.baidu.com");  
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPut);
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
