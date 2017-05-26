package com.beta.app.sync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SynchronizedTest {
    public static synchronized  void staticMethod(){
        
        System.out.println("staticMethod start");
        
        try {
            
            System.out.println("staticMethod sleep start");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("staticMethod end");
    }
public static synchronized  void staticMethod2(){
        
        System.out.println("staticMethod2 start");
        
        try {
            
            System.out.println("staticMethod2 sleep start");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("staticMethod2 end");
    }
    
    public synchronized void me1(){
        
        System.out.println("me1 start");
        
        try {
            
            System.out.println("me1 sleep start");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("me1 end");
    }
    
    
    public synchronized void me2(){
        
        System.out.println("me2 start");
        
        try {
            
            System.out.println("me2 sleep start");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("me2 end");
    }
    
    
    public void me3(){
        
        System.out.println("me3 start");
        
        try {
            
            System.out.println("me3 sleep start");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("me3 end");
    }
    
    public void me4(){
        synchronized(this){
            
            System.out.println("me4 start");
        }
        
        
        try {
            
            System.out.println("me4 sleep start");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("me4 end");
    }
    
    public static void main(String[] args) {
        final SynchronizedTest st = new SynchronizedTest();
        
        Executor  exe = Executors.newCachedThreadPool();
        
        exe.execute(new Runnable() {
                    
            @Override
            public void run() {
                SynchronizedTest.staticMethod();
                
            }
        });
        exe.execute(new Runnable() {
            
            @Override
            public void run() {
                SynchronizedTest.staticMethod2();
                
            }
        });
        
        exe.execute(new Runnable() {
            
            @Override
            public void run() {
                st.me1();
                
            }
        });
        
        exe.execute(new Runnable() {
            
            @Override
            public void run() {
                st.me2();
                
            }
        });
        exe.execute(new Runnable() {
            
            @Override
            public void run() {
                st.me4();
                
            }
        });
        
        exe.execute(new Runnable() {
            
            @Override
            public void run() {
                st.me3();
                
            }
        });
    }
}
