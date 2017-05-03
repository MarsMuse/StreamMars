package com.beta.prop.web.upload;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.beta.prop.web.upload.handler.UploadHandler;
import com.beta.prop.web.upload.observer.UploadObserver;

/**
 * 
 * @ClassName:  UploadServlet   
 * @Description:(上传文件的servlet)   
 * @author: mars
 * @date:   2017年4月28日 上午11:57:03   
 *     
 * @Copyright: 2017 
 *
 */
public class UploadServlet  extends  HttpServlet{
    
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 获取到日志实体r
     */
    private final static  Logger  log =  LoggerFactory.getLogger(UploadServlet.class);
    
    /**
     * 上传文件处理器
     */
    private UploadHandler uploadHandler;
    
    /**
     * 文件处理器观察者
     */
    private UploadObserver uploadObserver;
    /**
     * 
     * <p>Title: doPost</p>   
     * <p>Description: post请求处理</p>   
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException   
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取到文件上传处理器
        uploadHandler  =  uploadObserver.getInstance(request);
        
        //处理上传文件
        uploadHandler.handler(request, response);
    }


    /**
     * 
     * <p>Title: init</p>   
     * <p>Description: Servlet初始化函数</p>   
     * @param config
     * @throws ServletException   
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        log.debug("上传实例初始化");
        super.init(config);
        //获取到servlet上下文
        ServletContext  context  = config.getServletContext();
        //获取到Spring容器上下文
        WebApplicationContext  appContext   = WebApplicationContextUtils.getWebApplicationContext(context);
        
        //获取到spring容器中的上传观察者
        try {
            this.uploadObserver  =  appContext.getBean(UploadObserver.class);
        }catch (NoUniqueBeanDefinitionException e){
            log.error("应用上下文环境中的上传处理bean不唯一，上传组件初始化失败！", e);
        }catch (NoSuchBeanDefinitionException e){
            log.error("应用上下文环境中无法找到上传处理bean，上传组件初始化失败！", e);
        }catch (BeansException e){
            log.error("上传组件初始化失败！", e);
        }
    }


}
