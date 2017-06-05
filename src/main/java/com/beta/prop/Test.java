package com.beta.prop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test  {
    public static CopyOnWriteArrayList<String>  cowl =  new CopyOnWriteArrayList<>();
    private List<Integer>  dataList = new ArrayList<Integer>();
    public static   Integer  m = new Integer(0)  ;
    public static volatile long te = 0L;
    private int n = 0;
    public static AtomicInteger ai = new AtomicInteger(0);
    public static Lock  lock = new ReentrantLock();
    public  static void testLock(long a){
        lock.lock();
        System.out.println("时间："+System.currentTimeMillis()+"数字："+a);
        lock.unlock();
    }
    
    public   int add(){
        synchronized(this){
         
            return ++n;
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
        
        /*System.out.println(Integer.MAX_VALUE);
        Executor  exe =  Executors.newFixedThreadPool(1000);
        for(int i = 0 ; i < 1000000 ; i++){
            
            final int se = i;
            exe.execute(new Runnable() {
                
                @Override
                public void run() {
                    Test.testLock(Thread.currentThread().getId());
                }
            });
        }
        
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Test.cowl.size());*/
        
        /*SingleEnum a = SingleEnum.Instance;
        a.sys();
        
        System.out.println(a  instanceof  Object);
        
        
        Demo dn3 = new Dn3();
        
        Dn1 dn1 = new Dn1();
        
        System.out.println(dn3 instanceof Demo);
        System.out.println(dn3.getClass()  ==   Dn1.class);
        
       Objects.equals("", "");*/

        /*String[] a ={"aaa" ,"aaa" ,"aaa" ,"aaa" ,"aaa" ,"aaa" ,"aaa" ,"aaa" ,"aaa" };
        String[] b ={"b" ,"b" ,"b"};
        System.arraycopy(b, 0, a, 0, b.length);
        a[b.length] = null;
        for(String mm : a){
            System.out.println(mm);
        }*/
        /*char ch = 'a';
        int ma = Integer.MAX_VALUE +33333;
        System.out.println(ma);
        */
        
        String a = new String("asdo");
        String b = new String();
        
        System.out.println(Long.valueOf("mmm"));
    }
    
    
    static class Demo{}
    
     static class Dn1 extends Demo{}
    
    
     static class Dn2 extends Demo{}
    
   static class Dn3 extends Dn2{}
}
