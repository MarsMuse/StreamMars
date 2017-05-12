package com.beta.app.single;

public class SingleDemo {
    private  static SingleDemo singleDemo;
    private  SingleDemo(){}
    public static SingleDemo  getInstance(){
        if(singleDemo == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleDemo  =  new  SingleDemo();
            
        }
        return singleDemo;
    }
}
