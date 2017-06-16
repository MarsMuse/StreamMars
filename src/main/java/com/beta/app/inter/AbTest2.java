package com.beta.app.inter;

public  class AbTest2 implements Test2 {
  public void monkeyFly(){
      
      System.out.println("abClass");
  }
  
  public void getA(){
      this.a();
  }
  
  public void getB(){
	  AbTest2.this.b();
  }
  
  void a(){
	  System.out.println("wcxa");
  }
  
  void b(){
	  System.out.println("wcxa");
  }
  
   protected void say(){
      System.out.println("hehe");
  }
}
