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
			throw new IllegalArgumentException("��Ч���->�����ؼ��Ƿ�������.");
		} 
		if(!metaData.startsWith("control")){
			throw new IllegalArgumentException("��Ч���->�����ؼ��Ƿ�������.");
		}

		Map<String, String> map = new HashMap<String, String>();
		ComponentMeta data = new ComponentMeta();
		String[] array = metaData.split("(,|;)");
		for(String item : array){
			String[] cell =item.split(":");
			map.put(cell[0].trim(), cell[1].trim());
		}
		if(map.get("control") == null){
			throw new IllegalArgumentException("��Ч���->�ؼ�����Ϊ��.");
		}
		String controlName = map.get("control").toUpperCase();
		try { 
			data.setType(ComponentType.valueOf(controlName));
		} catch (Exception e) {
			throw new IllegalArgumentException("��Ч��� -> û���ҵ���Ӧ�Ŀؼ�:" + controlName);
		}
		String text = map.get("text");
		if(text != null){
			data.setText(text);
		}
		return data;
	}
}
