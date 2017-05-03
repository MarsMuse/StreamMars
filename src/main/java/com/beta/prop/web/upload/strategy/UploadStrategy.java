package com.beta.prop.web.upload.strategy;

import java.util.List;

/**
 * 
 * @ClassName:  UploadStrategy   
 * @Description:TODO(上传策略)   
 * @author: mars
 * @date:   2017年4月28日 下午4:42:39   
 *     
 * @Copyright: 2017 
 *
 */
public interface UploadStrategy {
    

	/**
	 * 
	 * @Title: setFileMaxSize   
	 * @Description: TODO(设置单个文件上传最大文件限制)   
	 * @param: @param maxSize      
	 * @return: void      
	 * @throws
	 */
    void  setFileMaxSize(long  maxSize);
    
    /**
     * 
     * @Title: getFileMaxSize   
     * @Description: TODO(获取到单个最大文件限制)   
     * @param: @return      
     * @return: long      
     * @throws
     */
    long  getFileMaxSize();
    
    /**
     * 
     * @Title: setAllMaxSize   
     * @Description: TODO(设置上传总大小限制)   
     * @param: @param allMaxSize      
     * @return: void      
     * @throws
     */
    void  setAllMaxSize(long  allMaxSize);
    
    /**
     * 
     * @Title: getAllMaxSize   
     * @Description: TODO(获取到上传文件总大小限制)   
     * @param: @return      
     * @return: long      
     * @throws
     */
    long  getAllMaxSize();
    
    
    /**
     * 
     * @Title: setBlackList   
     * @Description: TODO(设置文件类型黑名单)   
     * @param: @param blackList      
     * @return: void      
     * @throws
     */
    void  setBlackList(List<String>  blackList);
    
    /**
     * 
     * @Title: getBalckList   
     * @Description: TODO(获取到文件类型黑名单，限制具有威胁的文件上传到服务器)   
     * @param: @return      
     * @return: List<String>      
     * @throws
     */
    List<String>  getBalckList();
    
    
}
