package com.beta.prop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

public class Demo {
    
    public static void main(String[] args) {
        /*final   long a = System.currentTimeMillis();
        Test t = new Test();
        Executor exe = Executors.newFixedThreadPool(10000);
        for(int i = 0 ; i<800000 ;i++ ){
            exe.execute(new Runnable() {
                
                @Override
                public void run() {
                    t.getSynchronizedInfor(a);
                    
                }
            });
        }*/
        
        /*Set<HashProbobility>  obj = new HashSet<HashProbobility>();
        
        for(int i  = 0 ; i< 1000000 ; i++){
            HashProbobility hp = new HashProbobility();
            
            obj.add(hp);
        }
        
        System.out.println(obj.size());*/
        
        /*Object[] te = new Object[10];
        
        System.out.println(te.length);*/
        
       /* List<String>  arrayData  =   new ArrayList<String>();
        List<String>  linkData = new LinkedList<String>();
        Demo.initList(arrayData, 50000);
        Demo.initList(linkData, 50000);*/
        
        /*Demo.judge(arrayData);
        Demo.judge(linkData);
        
        Demo.loop(arrayData);
        Demo.loop(linkData);
        
        Demo.itera(arrayData);
        Demo.itera(linkData);*/
        
        String sql = "   maaaaaaaaaaf;   ";
        System.out.println(sql.length());
        sql = sql.trim();
        System.out.println(sql.length());
        int delIndex = sql.lastIndexOf(";");
        if(delIndex == (sql.length()-1)){
        	sql = sql.substring(0, sql.length()-1);
        }
        System.out.println(sql);
        System.out.println(sql.length());
    }
    
    static class  HashProbobility{
        
    }
    
    public static void  initList(List<String> map , int count){
        long startTime = System.currentTimeMillis();
        for(int i = 0 ; i<count ; i++){
            map.add(i+"");
        }
        long endTime = System.currentTimeMillis();
        System.out.println(map.getClass().getName()+"插入"+count/10000+"万数据耗时:"+(endTime - startTime));
    }
    
    public static void  judge(List<String>  data){
        
        
        if(data instanceof RandomAccess){
            
            System.out.println(data.getClass().getName()+  "实现了RandomAccess接口**********");
            long startTime = System.currentTimeMillis();
            
            for(int i = 0 ; i<data.size() ; i++){
                data.get(i);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("耗时:"+(endTime - startTime));
        }
        else{
            
            System.out.println(data.getClass().getName()+"未实现了RandomAccess接口**********");
            long startTime = System.currentTimeMillis();
            Iterator<String> it = data.iterator();
            while(it.hasNext()){
                it.next();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("耗时:"+(endTime - startTime));
        }
        
        
        
    }
    
    public static void loop(List<String>  data){
        
        System.out.println(data.getClass().getName()+  "进行for循环**********");
        long startTime = System.currentTimeMillis();
        for(int i = 0 ; i<data.size() ; i++){
            data.get(i);
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:"+(endTime - startTime));
    }
    
    public static void itera(List<String>  data){
        
        System.out.println(data.getClass().getName()+  "进行迭代器**********");
        long startTime = System.currentTimeMillis();
        Iterator<String> it = data.iterator();
        while(it.hasNext()){
            it.next();
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:"+(endTime - startTime));
    }

}
