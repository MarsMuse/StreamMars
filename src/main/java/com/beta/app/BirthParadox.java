package com.beta.app;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName:  BirthParadox   
 * @Description:TODO(生日悖论)   
 * @author: zouyao
 * @date:   2017年5月23日 下午3:05:54   
 *     
 * @Copyright: 2017 
 *
 */
public class BirthParadox {

    
    /**
     * 一年天数常量
     */
    private static final int  yearCount = 365;
    
    /**
     * 
     * @Title: getCurrentProb   
     * @Description: TODO(获取到当前人生日与前面人生日不同的概率)   
     * @param: @param index
     * @param: @return      
     * @return: Double      
     * @throws
     */
    private  Double  getCurrentProb(int index){
        
        Double result = 0.0;
        if(index > yearCount){
            throw new  RuntimeException();
        }
        
        result  =  (yearCount - index +1)/(double)yearCount;
        return result;
    }
    
    /**
     * 
     * @Title: getResult   
     * @Description: TODO(获取到所有人生日均不同的概率)   
     * @param: @param prob
     * @param: @return      
     * @return: double      
     * @throws
     */
    private double   getResult(List<Double>   prob){
        Double result = 1.0;
        for(Double data : prob){
            result *=data;
        }
        return result;
    }
    
    /**
     * 
     * @Title: getProbobility   
     * @Description: TODO(获取到存在有人生日相同的概率)   
     * @param: @param count
     * @param: @return      
     * @return: double      
     * @throws
     */
    public double  getProbobility(int count){
        Double result = 0.0;
        
         List<Double>   prob  =  new ArrayList<Double>();
         
         if(count >365){
             return 1;
         }
         else if( count <1){
             throw new RuntimeException();
         }
         
         for(int i = 1 ; i <= count ; i++){
             prob.add(this.getCurrentProb(i));
         }
         
         result = 1-this.getResult(prob);
             
         return result;
    }
    
    public static void main(String[] args) {
        BirthParadox bp = new BirthParadox();
        
        System.out.println(bp.getProbobility(23));
    }
}
