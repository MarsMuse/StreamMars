package com.beta.prop.web.utils;

import java.util.UUID;

/**
 * 
 * @ClassName:  UUIDUtil   
 * @Description:(UUID工具类)   
 * @author: mars
 * @date:   2017年4月28日 上午11:51:50   
 *     
 * @Copyright: 2017 
 *
 */
public class UUIDUtil {
    
    /**
     * 
     * @Title: getUUID   
     * @Description: TODO(获取到UUID去除了横线)   
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  static  String  getUUID(){
        String  uuid  =  UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
    
    private  UUIDUtil(){}
}
