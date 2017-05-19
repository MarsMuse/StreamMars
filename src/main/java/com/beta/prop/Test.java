package com.beta.prop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Test  {
    public static CopyOnWriteArrayList<String>  cowl =  new CopyOnWriteArrayList<>();
    private List<Integer>  dataList = new ArrayList<Integer>();
    public static   Integer  m = new Integer(0)  ;
    public static volatile long te = 0L;
    public static AtomicInteger ai = new AtomicInteger(0);
    

    
    public   int add(){
        synchronized(Test.m){
         
            return ++m;
        }
        
    }
    static synchronized int getm(){
        return ++m;
    }
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
    
    public static void main(String[] args) {
        
        System.out.println(Integer.MAX_VALUE);
        Executor  exe =  Executors.newCachedThreadPool();
        for(int i = 0 ; i < 10001 ; i++){
            
            final int se = i;
            exe.execute(new Runnable() {
                
                @Override
                public void run() {
                	Test t = new Test();
                    int a = t.add();
                    Test.cowl.addIfAbsent(a+"");
                    // System.out.println("数据："+a);
                    System.out.println("Count:"+Test.cowl.size());
                }
            });
        }
        
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Test.cowl.size());
        
    }
}
