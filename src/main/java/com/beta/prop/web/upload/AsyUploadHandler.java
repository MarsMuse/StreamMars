package com.beta.prop.web.upload;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beta.prop.web.upload.file.FileAbstractInfor;
import com.beta.prop.web.upload.strategy.UploadStrategy;

/**
 * 
 * @ClassName:  AsyUploadHandler   
 * @Description:TODO(同步文件上传处理器)   
 * @author: mars
 * @date:   2017年5月2日 下午2:25:34   
 *     
 * @Copyright: 2017 
 *
 */
public class AsyUploadHandler implements UploadHandler {
    //日志打印
    private  static  final  Logger  log  =  LoggerFactory.getLogger(AsyUploadHandler.class);
    //单线程（在加载的时候便会创建一个守护线程，不会销毁。）
    private  static  final  ExecutorService  singleThread =  Executors.newSingleThreadExecutor();
    //上传策略
    private    UploadStrategy  uploadStrategy;
    @Override
    public void loadRequestFiles(HttpServletRequest request, HttpServletResponse response) {
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
        
        //ServletFileUpload upload = new ServletFileUpload();
        String encoding = request.getCharacterEncoding();
        encoding = encoding != null ? encoding : DEFAULT_CHARSET_ENCODING;
    }

    @Override
    public List<FileAbstractInfor> saveFileToServer(HttpServletRequest request, HttpServletResponse response) {
        
        return null;
    }

    public UploadStrategy getUploadStrategy() {
        return uploadStrategy;
    }

    public void setUploadStrategy(UploadStrategy uploadStrategy) {
        this.uploadStrategy = uploadStrategy;
    }

    
}
