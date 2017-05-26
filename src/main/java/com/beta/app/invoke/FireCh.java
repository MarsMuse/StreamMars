package com.beta.app.invoke;


public class FireCh  extends Fire{

    public static void main(String[] args) {
        FireCh fc = new FireCh();
        fc.inv();
    }
    
    public void inv(){
        invo1();
    }
    
    public void invo1(int a){
        De de = new De();
        de.sys();
    }
    
    private class De{
        
        public De(){
            
            System.out.println("子类的被调用");
        }
        
        public void sys(){
            
            System.out.println("子类函数");
        }
    }
}
