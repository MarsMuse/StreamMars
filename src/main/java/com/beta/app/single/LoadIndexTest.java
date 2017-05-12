package com.beta.app.single;

public class LoadIndexTest {
	{
        
        System.out.println("out  primary code  block  loading");
    }
    
    
    static {
        
        System.out.println("out static  code block  loading");
    }
    private static class Demo{
    	
    	private static String name = "a";
        {
            
            System.out.println("static  in  primary code  block  loading");
        }
        
        
        static {
            
            System.out.println("static  in static  code block  loading");
        }
    }
    
    
    public  class Demo1{
        {
            
            System.out.println("static  in  primary code  block  loading");
        }
        
    }
    
    public static void get(){
    	System.out.println(Demo.name);
    }
}
