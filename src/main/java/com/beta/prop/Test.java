package com.beta.prop;

import java.util.ArrayList;
import java.util.List;

public class Test  {

    private List<Integer>  dataList = new ArrayList<Integer>();
    int a = 0;
    public     void  getSynchronizedInfor(long data){
       
        System.out.println(Thread.currentThread().getName() +"-->数据："+a);
        a++;
        System.out.println(System.currentTimeMillis()-data);
    }
    
    public void show(){
        StringBuffer ab = new StringBuffer();
        
        for(int re : dataList){
            ab.append(re+"-");
        }
        System.out.println("数据："+ab.toString());
        
    }
}
