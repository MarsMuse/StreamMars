package com.beta.prop.web.upload.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beta.prop.web.upload.file.FileAbstractInfor;

/**
 * 
 * @ClassName:  SynUploadHandler   
 * @Description:TODO(异步文件上传处理器)   
 * @author: mars
 * @date:   2017年5月2日 下午2:25:09   
 *     
 * @Copyright: 2017 
 *
 */
public class UploadFileSystemHandler extends UploadHandler {
    //日志打印
    private  static  final  Logger  log  =  LoggerFactory.getLogger(UploadFileSystemHandler.class);


    @Override
    public List<FileAbstractInfor> saveFileToServer(HttpServletRequest request, HttpServletResponse response) {
        
        return null;
    }

}
