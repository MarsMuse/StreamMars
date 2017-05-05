package com.beta.prop.web.upload.observer;

import javax.servlet.http.HttpServletRequest;

import com.beta.prop.web.upload.handler.UploadFileSystemHandler;
import com.beta.prop.web.upload.handler.UploadHandler;
import com.beta.prop.web.upload.handler.UploadLocalHandler;


/**
 * 
 * @ClassName:  FileUploadObserver   
 * @Description:TODO(文件上传处理器观察者 ， 根据请求标志位的不同  使用不同的文件处理对象 ，实现 文件上传的解耦合 单一职责)   
 * @author: mars<FireMonkeyFrame@163.com>
 * @date:   2017年5月3日 下午5:53:17   
 *     
 * @Copyright: 2017 
 *
 */
public class FileUploadObserver implements UploadObserver {
    
    //上传到文件服务器的处理器
    private  UploadFileSystemHandler    ufsHandler;
    
    //上传到本地服务器的处理器
    private  UploadLocalHandler  ulHandler;
    
    @Override
    public UploadHandler getInstance(HttpServletRequest request) {
        //获取到文件上传目标
        String  target  =  request.getParameter("target");
        //如果目标为local 则表示上传到本地应用服务器
        if("local".equals(target)){
            return  ulHandler;
        }
        else{
            return  ufsHandler;
        }
    }

    public UploadFileSystemHandler getUfsHandler() {
        return ufsHandler;
    }

    public void setUfsHandler(UploadFileSystemHandler ufsHandler) {
        this.ufsHandler = ufsHandler;
    }

    public UploadLocalHandler getUlHandler() {
        return ulHandler;
    }

    public void setUlHandler(UploadLocalHandler ulHandler) {
        this.ulHandler = ulHandler;
    }
    
    
}
