package com.beta.app.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpPoolUtil {
    
    //最大线程
    private static final int MAX_TOTAL = 1024;
    //单个路由默认启动链路数量
    private static final int MAX_ROUTE_DEF = 256;
    //路由最大链路数量
    //private static final int MAX_ROUTE_TOTAL=200;
    //默认连接超时
        private static int TIMEOUT_DEF  = 30*1000;
    //http连接管理对象
    private static CloseableHttpClient  httpClient;
    //连接池管理
    private static PoolingHttpClientConnectionManager  poolManage;
    
    static{
        init();
    }
    private static void init(){
        //获取到连接管理工具
        poolManage = new PoolingHttpClientConnectionManager();
        poolManage.setMaxTotal(MAX_TOTAL);
        poolManage.setDefaultMaxPerRoute(MAX_ROUTE_DEF);
       // poolManage.setMaxPerRoute(new HttpRoute(new HttpHost("localhost", 8008)) , MAX_ROUTE_TOTAL);
        
        final RequestConfig config = RequestConfig.custom().setSocketTimeout(TIMEOUT_DEF)
                .setConnectTimeout(TIMEOUT_DEF).setConnectionRequestTimeout(TIMEOUT_DEF).build();
        
        httpClient = HttpClients.custom().setConnectionManager(poolManage).setDefaultRequestConfig(config).build();
        //启动定时器清理连接
        closeConnectionTask(5);
    }
    
    private static void closeConnectionTask(final int timeout){
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    try {
                        
                        TimeUnit.SECONDS.sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    poolManage.closeExpiredConnections();
                }
            }
        }).start();
    }
    
    public static String httpPost(String url) throws ClientProtocolException, IOException{
        if(url == null || "".equals(url.trim())){
            return null;
        }
        String result = httpPost(url ,"");
        return result;
    }
    
    public static String httpPost(String url  , String content) throws ClientProtocolException, IOException{
        if(url == null || "".equals(url.trim())){
            return null;
        }
        String result = null;
        CloseableHttpResponse resPost = null;
        try{
            HttpPost  post = new HttpPost(url);
            if(content != null && (!"".equals(content))){
                HttpEntity form = new StringEntity(content, ContentType.APPLICATION_JSON);
                post.setEntity(form);
            }
            resPost = httpClient.execute(post);
            HttpEntity entity = resPost.getEntity();
            result = EntityUtils.toString(entity );
        }finally{
            if(resPost != null){
                resPost.close();
            }
        }
        return result;
    }
    
    public static String httpPost(String url  , String fieldName , String value) throws ClientProtocolException, IOException{
        if(url == null || "".equals(url.trim())){
            return null;
        }
        String result = null;
        CloseableHttpResponse resPost = null;
        try{
            HttpPost  post = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair(fieldName , value));
            HttpEntity  form =new UrlEncodedFormEntity(nvps);
            post.setEntity(form);
            resPost = httpClient.execute(post);
            HttpEntity entity = resPost.getEntity();
            result = EntityUtils.toString(entity );
        }finally{
            if(resPost != null){
                resPost.close();
            }
        }
        return result;
    }
    
    public static String httpPost(String url  , Map<String  ,  String>  formData) throws ClientProtocolException, IOException{
        if(url == null || "".equals(url.trim())){
            return null;
        }
        String result = null;
        CloseableHttpResponse resPost = null;
        try{
            HttpPost  post = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String>  keySet = formData.keySet();
            for(String key : keySet){
                String value = formData.get(key);
                nvps.add(new BasicNameValuePair(key , value));
            }
            HttpEntity  form =new UrlEncodedFormEntity(nvps);
            post.setEntity(form);
            resPost = httpClient.execute(post);
            HttpEntity entity = resPost.getEntity();
            result = EntityUtils.toString(entity );
        }finally{
            if(resPost != null){
                resPost.close();
            }
        }
        return result;
    }
    
    public static String httpGet(String url) throws ClientProtocolException, IOException{
        if(url == null || "".equals(url.trim())){
            return null;
        }
        String result = null;
        CloseableHttpResponse resPost = null;
        try{
            HttpGet  get= new HttpGet(url);
            resPost = httpClient.execute(get);
            HttpEntity entity = resPost.getEntity();
            result = EntityUtils.toString(entity);
        }finally{
            if(resPost != null){
                resPost.close();
            }
        }
        return result;
    }
    
}
