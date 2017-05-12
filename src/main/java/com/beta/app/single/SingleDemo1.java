package com.beta.app.single;

public class SingleDemo1 {
    private final static   SingleDemo1   sd= new SingleDemo1();
    private  SingleDemo1(){}
    public static SingleDemo1  getInstance(){
        
        return sd;
    }
} 
