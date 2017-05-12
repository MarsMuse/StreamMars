package com.beta.prop;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Demo {
    
    public static void main(String[] args) {
        final   long a = System.currentTimeMillis();
        Test t = new Test();
        Executor exe = Executors.newFixedThreadPool(10000);
        for(int i = 0 ; i<800000 ;i++ ){
            exe.execute(new Runnable() {
                
                @Override
                public void run() {
                    t.getSynchronizedInfor(a);
                    
                }
            });
        }
    }
}
