package com.beta.app.inter;

public class ImplTest  extends AbTest2 implements Test2 ,Test1{

    @Override
    public void monkeyFly() {
        System.out.println("Clss");
        
    }
    
    public static void main(String[] args) {
    	AbTest2 imp = new ImplTest();
        imp.monkeyFly();
        imp.say();
    }
    
     void say(int a){
        System.out.println(a);
    }
}
