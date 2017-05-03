package com.beta.prop.web.upload.observer;

import javax.servlet.http.HttpServletRequest;

import com.beta.prop.web.upload.handler.UploadHandler;


/**
 * 
 * @ClassName:  UploadObserver   
 * @Description:TODO(文件上传观察者 通过观察request的变化判断是上传到本地服务器还是文件服务器)   
 * @author: mars<FireMonkeyFrame@163.com>
 * @date:   2017年5月3日 下午5:38:47   
 *     
 * @Copyright: 2017 
 *
 */
public interface UploadObserver {

    UploadHandler  getInstance(HttpServletRequest  request);
}
