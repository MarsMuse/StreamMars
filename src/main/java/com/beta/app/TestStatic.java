package com.beta.app;

public class TestStatic {
    static int a = 1;
    static long count = 0;
    static void ma(int c){
        System.out.println(a);
        System.out.println(b);
        a = c;
    }
    static int b = 3;
    
    static{
        
        int cc = 11;
        
        a = cc;
    }
    static {
        System.out.println("static block loading...");
        System.out.println(a== 1);
        System.out.println(b);
    }
    
    int mm = 0;
    {
        count ++;
        a = 9999;
        System.out.println(a);
    }
    int nn = 3;
    
    public void show(){
        System.out.println(a);
    }
    
    static void showCount(){
        
        System.out.println("此类实例化了"+count+"次");
    }
    
    public TestStatic(){
        
        a++;
    }
    public static void main(String[] args) {
        
        
       /* for(int i = 0 ; i< 100000 ; i++){
            TestStatic ts = new TestStatic();
        }
        TestStatic.showCount();*/
        
        TestStatic ts = new TestStatic();
        
        ts.show();
    }
    
}
