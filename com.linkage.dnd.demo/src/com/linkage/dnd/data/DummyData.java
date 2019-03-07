package com.linkage.dnd.data;

import java.util.HashMap;
import java.util.Map;

public class DummyData {

	private static String[] listItems = null;
	
	private static Map<String, Object> data = new HashMap<String, Object>();

	public static String JAVA_OBJECT = "javaObject";
	
	static {
		listItems = new String[]{"txt1.txt", "txt2.txt", "button", "label", JAVA_OBJECT};
		
		data.put(listItems[0], "这只是实现了简单的控件和控件之间的拖拽。 现在我们来实现两个功能，从导航器或者操作系统里向编辑器中拖拽。首先是导航器，大部分导航器使用了TreeViewer展示，org.eclipse.jface.viewers.StructuredViewer#addDragSupport方法为TreeViewer的Control添加了DragSource即是创建DragSource的步骤已经预先完成了。");
		data.put(listItems[1], "中国Java开发网 - 请问如何获得SWT中List widget当前选中的项目");
		data.put(listItems[2], "control:button, text:hello");
		data.put(listItems[3], "control:label, text:你好"); 
		data.put(listItems[4], new JavaObject(23, "Jack", 1));
	}
	
	public static Map<String, Object> getInitData(){
		return data;
	}
	
	public static String[] getListItems(){
		return listItems;
	}
}
