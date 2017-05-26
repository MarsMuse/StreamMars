package com.beta.app.invoke;

public class Fire {

	public Fire(){
		System.out.println("父类构造器调用");
	}
	
    public void inv(){
        
    }
    
    public void invo1(int a){
        De de = new De();
        de.sys();
    }
    public void invo1(){
        invo1(0);
    }
    private class De{
        
        public De(){
            
            System.out.println("父类的被调用");
        }
        
        public void sys(){
            
            System.out.println("父类函数");
        }
    }
}
