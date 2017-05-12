package com.beta.prop.web.upload.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beta.prop.web.upload.file.FileAbstractInfor;
import com.beta.prop.web.upload.strategy.UploadStrategy;

/**
 * 
 * @ClassName:  UploadHandler   
 * @Description:TODO(文件上传处理器)   
 * @author: mars
 * @date:   2017年4月28日 下午12:09:47   
 *     
 * @Copyright: 2017 
 *
 */
public abstract class UploadHandler {
    //服务器异常（文件处理出现错误）
    static  final  int  HTTP_CODE_SERVER_ERR = 500;
    //客户端异常（上传具有威胁的黑名单文件文件）
    static  final  int  HTTP_CODE_CLIENT_ERR = 400;
    //设置默认的编码集
    static  final  String  DEFAULT_CHARSET_ENCODING ="UTF-8";
    //设置响应内容
    static  final  String  DEFAULT_RESPONSE_CONTENT_TYPE = "application/json";
    //返回的JSON数组开始括号
    static  final  String  JSON_ARRAY_START = "[";
    //返回的JSON数组结束括号
    static  final  String  JSON_ARRAY_END = "]";
    //日志
    private  Logger  log  =  LoggerFactory.getLogger(UploadHandler.class);
    //上传策略处理
    private  UploadStrategy  uploadStrategy;
    /**
     * 
     * @Title: hanler   
     * @Description: TODO(处理上传文件)   
     * @param: @param request
     * @param: @param response      
     * @return: void      
     * @throws
     */
    public   void   handler(HttpServletRequest request, HttpServletResponse response){
        
    }
    /**
     * 
     * @Title: loadRequestFiles   
     * @Description: TODO(加载请求里面的文件)   
     * @param: @param request
     * @param: @param response      
     * @return: void      
     * @throws
     */
    public   void  loadRequestFiles(HttpServletRequest request, HttpServletResponse response){
        log.debug("同步上传-->开始加载上传文件");
        //判断上传策略组件是否加载成功
        if(uploadStrategy == null){
            log.error("上传组件未初始化，请检测是否配置上传策略组件。");
            try {
                response.sendError(HTTP_CODE_SERVER_ERR);
            } catch (IOException e) {
                log.error("响应网络IO异常");
                e.printStackTrace();
            }
            return;
        }
        //获取到一个servlet文件上传对象
        ServletFileUpload upload = new ServletFileUpload();
        //获取客户端字符集
        String encoding = request.getCharacterEncoding();
        //设置字符集若客户端未设置字符集，则设置为默认字符集UTF-8
        encoding = encoding != null ? encoding : DEFAULT_CHARSET_ENCODING;
        //设置头字符集
        upload.setHeaderEncoding(encoding);
        //限制单个文件大小
        upload.setFileSizeMax(uploadStrategy.getFileMaxSize());
        //限制上传总大小
        upload.setSizeMax(uploadStrategy.getAllMaxSize());
        //上传响应数据 拼接成为为JSON字符串用于返回到前台(线程安全)
        StringBuilder  requestData = new StringBuilder(JSON_ARRAY_START);
        //上传文件迭代器
        FileItemIterator  uploadFileIterator  =  null;
        //通过commons-upload对象从request获取到文件上传迭代器
        try {
            uploadFileIterator  =  upload.getItemIterator(request);
            //循环获取到上传文件
            while(uploadFileIterator.hasNext()){
                FileItemStream  fileItem    =    uploadFileIterator.next();
                //如果不是通过表单提交
                if(!fileItem.isFormField()){
                    //获取到客户端文件名 若客户端为IE则会上传文件的全路径
                    String  clientFileName  =  fileItem.getName();
                    if(clientFileName != null){
                        //通过工具获取到真正文件名
                        clientFileName  =  FilenameUtils.getName(clientFileName);
                    }
                    //获取到客户端后缀名
                    String  clientExtName = FilenameUtils.getExtension(clientFileName);
                    //经过黑名单过滤
                    if( clientExtName != null){
                        //获取到黑名单
                        List<String>  blackList = uploadStrategy.getBalckList();
                        //验证此文件是在黑名单之中
                        if(blackList != null && blackList.contains(clientExtName.toLowerCase())){
                            //在服务器打印出存在上传具有威胁文件的主机与文件名
                            log.warn("来自IP：【{}】的上传有安全威胁的文件行为被阻止。文件名：{}",
                                    request.getRemoteAddr(), clientFileName);
                            //提示客户端出错
                            response.sendError(HTTP_CODE_CLIENT_ERR);
                            //结束上传
                            return;
                        }
                        //获取到客户端内容类型
                        String  contentType  =  fileItem.getContentType();
                        
                        
                    }
                    
                    
                }
            }
        } catch (FileUploadException e) {
            log.error("{}:文件上传IO出现异常",Thread.currentThread().getName());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("{}:出现IO出现异常",Thread.currentThread().getName());
            e.printStackTrace();
        }
    }
    
    
    //获取到上传处理策略
    public UploadStrategy getUploadStrategy() {
        return uploadStrategy;
    }
    //设置上传处理策略
    public void setUploadStrategy(UploadStrategy uploadStrategy) {
        this.uploadStrategy = uploadStrategy;
    }
    /**
     * 
     * @Title: saveFileToServer   
     * @Description: TODO(存储文件到本地服务器)   
     * @param: @param request
     * @param: @param response
     * @param: @return      
     * @return: List<FilePath>      
     * @throws
     */
    public abstract List<FileAbstractInfor>  saveFileToServer(HttpServletRequest request, HttpServletResponse response);
    
    
    
}
