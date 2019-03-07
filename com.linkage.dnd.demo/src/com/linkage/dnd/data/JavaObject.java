package com.linkage.dnd.data;

import java.io.Serializable;

public class JavaObject implements Serializable {

	private int age;
	private String name;
	private int gender;
	
	public JavaObject(int age, String name, int gender){
		this.age = age;
		this.name = name;
		this.gender = gender;
	}
	@Override
	public String toString() {
		 
		return String.format("%s: age %d, gender %d", this.name, this.age, this.gender);
	}
}
