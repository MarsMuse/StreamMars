package com.beta.app;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class InstanceCount {

    public static AtomicLong al = new AtomicLong(0);
    
    {
        
        
        System.out.println("此类被线程:"+Thread.currentThread().getId()+"完成第"+al.getAndIncrement()+"次实例化");
    }
    
    public static void main(String[] args) {
        Executor exe = Executors.newCachedThreadPool();
        
        for(int i = 0 ; i<1000 ; i++){
            exe.execute(new Runnable() {
                
                @Override
                public void run() {
                	InstanceCount ic = new InstanceCount();
                    
                }
            });
        }
    }
}
