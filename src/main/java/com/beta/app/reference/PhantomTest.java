package com.beta.app.reference;
public class PhantomTest   {
    
    public static void main(String[] args) {
        StackTraceElement[]  steArray= Thread.currentThread().getStackTrace();
        for(int i = 0 ; i < steArray.length ; i++){
            StackTraceElement ste = steArray[i];
            System.out.println("类名："+ste.getClassName());
            System.out.println("方法："+ste.getMethodName());
        }
        
        String[][]  aa =  new String[][]{{"aaa","aaaa"}};
        System.out.println(aa.length);
        System.out.println(aa[0].length);
        aa = new String[0][];
        System.out.println(aa.length);
        
        PhantomTest.getLock();
        PhantomTest.getLock(0);
        System.out.println(PhantomTest.getInfo());
    }
    
    public static  void getLock(){
        StackTraceElement ste = Thread.currentThread().getStackTrace()[1];
        System.out.println("类名："+ste.getClassName()+"方法名："+ste.getMethodName());
    }
    public static void getLock(int i){
        StackTraceElement ste = Thread.currentThread().getStackTrace()[1];
        System.out.println("类名："+ste.getClassName()+"方法名："+ste.getMethodName());
    }
    
    public static int getInfo(){
    	
    	int a = 20;
    	
    	while(a > 0){
    		try{
    			return a;
    		}catch(Exception e){
    			
    		}
    		
    		a--;
    	}
    	
    	return 0;
    }
}
