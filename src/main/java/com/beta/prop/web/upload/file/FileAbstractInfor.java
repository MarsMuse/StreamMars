package com.beta.prop.web.upload.file;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName:  FileAbstractInfor   
 * @Description:TODO(抽象的文件信息)   
 * @author: mars
 * @date:   2017年4月28日 下午1:25:44   
 *     
 * @Copyright: 2017 
 *
 */
public abstract class FileAbstractInfor   implements  Serializable  , Cloneable{


    /**
     * 可序列化
     */
    private static final long serialVersionUID = 110253999758732161L;
    
    /**
     * 输出日志log
     */
    private static final Logger  log  =  LoggerFactory.getLogger(FileAbstractInfor.class);
    //文件名
    private  String  fileName;
    
    //文件后缀名
    private  String  extName;
    
    //文件大小
    private  String  size;

    public Object clone() {
        
        log.debug("克隆文件信息");
        
        FileAbstractInfor fileAbstractInfor  =  null;
        try {
            fileAbstractInfor  =  (FileAbstractInfor) super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("克隆文件信息失败");
            e.printStackTrace();
            
        }
        return fileAbstractInfor;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}
