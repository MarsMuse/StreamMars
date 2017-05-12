package com.beta.prop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DruidDemo   implements  InvocationHandler{
    private Connection  conn;
    
    
    public DruidDemo(Connection  conn) {
        super();
        this.conn = conn;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        return null;
    }

}
