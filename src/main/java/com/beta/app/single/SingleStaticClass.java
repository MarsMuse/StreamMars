package com.beta.app.single;

public class SingleStaticClass {
    private  static class  SscInstance{
        private  static  final  SingleStaticClass  ssc  =  new  SingleStaticClass();
    }
    private SingleStaticClass(){}
    
    public static  SingleStaticClass  getInstance(){
        return  SscInstance.ssc;
    }
}
