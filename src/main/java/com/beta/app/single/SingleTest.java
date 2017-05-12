package com.beta.app.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingleTest {
public static void main(String[] args) {
	System.out.println(SingleDemo1.getInstance().hashCode());
    @SuppressWarnings("rawtypes")
    Class  a = null;
    try {
         a = Class.forName("com.beta.app.single.SingleDemo1");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    Constructor con = null;
    try {
		con =  a.getDeclaredConstructor();
		con.setAccessible(true);
	} catch (NoSuchMethodException e1) {
		e1.printStackTrace();
	} catch (SecurityException e1) {
		e1.printStackTrace();
	}
    try {
    	System.out.println(SingleDemo1.getInstance().hashCode());
    	SingleDemo1  ssc  =  (SingleDemo1)con.newInstance();
        System.out.println(ssc.hashCode());
        
        System.out.println(con.newInstance().hashCode());
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	}
}
}
