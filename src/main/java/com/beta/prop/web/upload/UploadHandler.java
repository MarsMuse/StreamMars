package com.beta.prop.web.upload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.beta.prop.web.upload.file.FileAbstractInfor;

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
public interface UploadHandler {
    //服务器异常
    static final int HTTP_CODE_SERVER_ERR = 500;
    //客户端异常
    static final int HTTP_CODE_CLIENT_ERR = 400;
    //设置默认的编码集
    static final String DEFAULT_CHARSET_ENCODING ="UTF-8";
    //设置响应内容
    static final String DEFAULT_RESPONSE_CONTENT_TYPE = "application/json";
    //返回的JSON的数组括号
    static final String JSON_ARRAY_END = "]";
    /**
     * 
     * @Title: loadRequestFiles   
     * @Description: TODO(加载请求里面的文件)   
     * @param: @param request
     * @param: @param response      
     * @return: void      
     * @throws
     */
    public  void  loadRequestFiles(HttpServletRequest request, HttpServletResponse response);
    
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
    public List<FileAbstractInfor>  saveFileToServer(HttpServletRequest request, HttpServletResponse response);
}
