package com.linkage.dnd.data;

public class Constants {

	public static final String PREFIX_COMPONENT= "COMPONENT_";

	public static final String PREFIX_JAVA_OBJECT= "JAVA_OBJECT_";
	
	public static final String PREFIX_TEXT= "TEXT_asdf";
	
	public static final String TEMPLATE = "传递了一个Java{%s}对象到%s组件%s内";
	
	public static String getPrefix(String str){
		if(str != null && str.length() > 0){
			if(str.startsWith(Constants.PREFIX_COMPONENT)) return Constants.PREFIX_COMPONENT;
			if(str.startsWith(Constants.PREFIX_JAVA_OBJECT)) return Constants.PREFIX_JAVA_OBJECT;
			if(str.startsWith(Constants.PREFIX_TEXT)) return Constants.PREFIX_TEXT;
		}
		return "";
	}
}
