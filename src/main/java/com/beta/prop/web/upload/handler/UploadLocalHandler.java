package com.beta.prop.web.upload.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beta.prop.web.upload.file.FileAbstractInfor;

/**
 * 
 * @ClassName:  AsyUploadHandler   
 * @Description:(文件到本地上传处理器)   
 * @author: mars<FireMonkeyFrame@163.com>
 * @date:   2017年5月2日 下午2:25:34   
 *     
 * @Copyright: 2017 
 *
 */
public class UploadLocalHandler  extends UploadHandler {
    //日志打印
    private  static  final  Logger  log  =  LoggerFactory.getLogger(UploadLocalHandler.class);
    


    @Override
    public List<FileAbstractInfor> saveFileToServer(HttpServletRequest request, HttpServletResponse response) {
        log.debug("存储文件到应用服务器");
        return null;
    }



    
}
