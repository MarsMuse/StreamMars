package com.beta.prop.web.upload.strategy;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @ClassName:  UploadFileStrategy   
 * @Description:TODO(文件上传策略)   
 * @author:  mars<FireMonkeyFrame@163.com>
 * @date:   2017年5月4日 下午1:49:42   
 *     
 * @Copyright: 2017 
 *
 */
public class UploadFileStrategy implements UploadStrategy {
    
    //黑名单分隔符
    private  static  final  String  EXT_SPLITER = ",";   
    //从mb转换为byte需要计算的位数
    private  static  final  int  MB_CAST_BYTE  =  20;
    
    //限制单个文件大小
    private  long  fileMaxSize  = -1 ;
    
    //限制总文件大小
    private  long  allMaxSize  = -1;
    
    //黑名单列表
    private  List<String>  blackList;
    @Override
    public void setFileMaxSize(long maxSize) {
            this.fileMaxSize  =  maxSize;

    }

    @Override
    public long getFileMaxSize() {
            
        return this.fileMaxSize;
    }

    @Override
    public void setAllMaxSize(long allMaxSize) {
        this.allMaxSize  = allMaxSize;

    }

    @Override
    public long getAllMaxSize() {
            
        return  this.allMaxSize ;
    }

    @Override
    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;

    }

    @Override
    public List<String> getBalckList() {
            
        return this.blackList;
    }
    
    /**
     * 
     * @Title: setFileMbMax   
     * @Description: TODO(设置文件最大限制 MB)   
     * @param: @param fileMbMax      
     * @return: void      
     * @throws
     */
    public  void  setFileMbMax(long  fileMbMax){
        
        this.fileMaxSize = fileMbMax<<MB_CAST_BYTE;
    }
    
    public  long  getFileMbMax(){
        
        return  this.fileMaxSize>>MB_CAST_BYTE;
    }

    /**
     * 
     * @Title: setAllMbMax   
     * @Description: TODO(设置上传最大限制 MB)   
     * @param: @param allMbMax      
     * @return: void      
     * @throws
     */
    public  void  setAllMbMax(long  allMbMax){
        
        this.allMaxSize = allMbMax<<MB_CAST_BYTE;
    }
    
    public  long  getAllMbMax(){
        
        return  this.allMaxSize>>MB_CAST_BYTE;
    }
    
    /**
     * 
     * @Title: setBlockString   
     * @Description: TODO(设置黑名单-->通过字符串来设置)   
     * @param: @param blockString      
     * @return: void      
     * @throws
     */
    public  void  setBlockString(String  blockString){
    
        this.blackList  =  Arrays.asList(blockString.trim().toLowerCase().split(EXT_SPLITER));
    }
    
    public  String  getBlockString(){
        StringBuilder  blockBuffer  =  new  StringBuilder();
        
        for(String  temp  :  blackList){
            blockBuffer.append(temp+EXT_SPLITER);
        }
        blockBuffer.deleteCharAt(blockBuffer.length()-1);
        return  blockBuffer.toString();
        
    }
}
