package com.beta.prop.web.upload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beta.prop.web.upload.file.FileAbstractInfor;

/**
 * 
 * @ClassName:  UploadHandler   
 * @Description:TODO(文件上传处理器)   
 * @author: 邹尧
 * @date:   2017年4月28日 下午12:09:47   
 *     
 * @Copyright: 2017 
 *
 */
public interface UploadHandler {

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
