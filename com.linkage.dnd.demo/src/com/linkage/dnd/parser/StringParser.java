package com.linkage.dnd.parser;

import java.util.HashMap;
import java.util.Map;

import com.linkage.dnd.controls.ComponentMeta;
import com.linkage.dnd.controls.ComponentType;

public class StringParser implements Parser<String>{

	private StringParser(){
		
	}
	
	private static StringParser instance = new StringParser();
	
	public static Parser<String> getInstance(){
		return instance;
	}
	
	public ComponentMeta parse(String metaData){
		if(metaData == null || metaData.trim().length() == 0){
			throw new IllegalArgumentException("无效组件->解析控件是发生错误.");
		} 
		if(!metaData.startsWith("control")){
			throw new IllegalArgumentException("无效组件->解析控件是发生错误.");
		}

		Map<String, String> map = new HashMap<String, String>();
		ComponentMeta data = new ComponentMeta();
		String[] array = metaData.split("(,|;)");
		for(String item : array){
			String[] cell =item.split(":");
			map.put(cell[0].trim(), cell[1].trim());
		}
		if(map.get("control") == null){
			throw new IllegalArgumentException("无效组件->控件名称为空.");
		}
		String controlName = map.get("control").toUpperCase();
		try { 
			data.setType(ComponentType.valueOf(controlName));
		} catch (Exception e) {
			throw new IllegalArgumentException("无效组件 -> 没有找到对应的控件:" + controlName);
		}
		String text = map.get("text");
		if(text != null){
			data.setText(text);
		}
		return data;
	}
}
