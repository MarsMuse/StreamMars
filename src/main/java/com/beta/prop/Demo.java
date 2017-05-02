package com.beta.prop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    
    public static void main(String[] args) {
    	System.out.println(Thread.currentThread());
        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();  
          for (int i = 0; i < 100; i++) {  
        	  for(int j = 0 ;j <200;j++){
           final int index = j;  
           singleThreadExecutor.execute(new Runnable() {  
            public void run() {  
              System.out.println(index);
              System.out.println(Thread.currentThread());
              try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
            });
           
          }
           try {
               Thread.sleep(5000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
          }
    }
}
