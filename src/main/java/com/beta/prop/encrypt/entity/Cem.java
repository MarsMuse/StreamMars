package com.beta.prop.encrypt.entity;

public class Cem {
private String id;
	
	private String name;
	
	private String age;
	
	private String aa;
	
	private int a;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAa() {
		return aa;
	}

	public void setAa(String aa) {
		this.aa = aa;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public Cem(String id, String name, String age, String aa, int a) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.aa = aa;
		this.a = a;
	}

	public Cem() {
	}
	
}
