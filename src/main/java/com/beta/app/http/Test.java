package com.beta.app.http;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test {
    
    
    public static void main(String[] args) {
        Executor  exe = Executors.newFixedThreadPool(1000);
        for(int i = 0 ; i< 20 ; i++){
            final int count = i;
            exe.execute(new Runnable() {
                @Override
                public void run() {
                    /*HttpSingleton hs = HttpSingleton.getInstance();
                    hs.simpleGetInfo("http://127.0.0.1:8008/StreamMars/ws/api/batchData/singleCharge" ,count);*/
                   /* CloseableHttpClient httpClient = HttpClients.createDefault();
                     try{
                         HttpGet  get = new HttpGet("http://127.0.0.1:8008/StreamMars/ws/api/batchData/singleCharge");
                         CloseableHttpResponse response =  httpClient.execute(get);
                         HttpEntity entity = response.getEntity();
                         String info = EntityUtils.toString(entity);
                         System.out.println("当前线程号："+Thread.currentThread().getName()+"--->返回数据长度："+info.length()+"循环数:"+count);
                         EntityUtils.consume(entity);
                     }catch(Exception e){
                         e.printStackTrace();
                     }finally{
                         try {
                            httpClient.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                     }*/
                
                    try {
                        String infor = HttpPoolUtil.httpGet("http://127.0.0.1:8008/StreamMars/ws/api/batchData/singleCharge");
                        System.out.println("返回结果长度："+infor.length()+"结果为："+infor);
                    } catch (ClientProtocolException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                
                }
            });
            
        }
        
        
    }
}
