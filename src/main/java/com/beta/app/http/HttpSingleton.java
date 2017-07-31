package com.beta.app.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpSingleton {
    private static CloseableHttpClient  httpClient = null;
    
    static{
    	
        //在静态代码块获取到HttpClient对象
        httpClient = HttpClients.createDefault();
    }
    
    private HttpSingleton(){}
    
    private static  class SingletonInnerClass{
        private final static  HttpSingleton hs = new HttpSingleton();
    }
    
    public  static  HttpSingleton  getInstance(){
        return  SingletonInnerClass.hs;
    }
    
    public  void  simpleGetInfo(String url ,int i){
        CloseableHttpResponse response = null;
        try{
            HttpGet  get = new HttpGet(url);
            response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            String info = EntityUtils.toString(entity);
            System.out.println("当前线程号："+Thread.currentThread().getName()+"--->返回数据长度："+info.length()+"循环数:"+i);
            EntityUtils.consume(entity);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    System.out.println("响应连接关闭，出现异常情况");
                    e.printStackTrace();
                }
            }
            
            /*try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("连接已经被关闭，出现异常情况");
                e.printStackTrace();
            }*/
        }
        
    }
    
    public  void  simpleGetInfo(int i){
    	simpleGetInfo("https://www.baidu.com",i);
    }
}
