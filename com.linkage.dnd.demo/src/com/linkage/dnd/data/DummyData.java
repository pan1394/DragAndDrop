package com.linkage.dnd.data;

import java.util.HashMap;
import java.util.Map;

public class DummyData {

	private static String[] listItems = null;
	
	private static Map<String, Object> data = new HashMap<String, Object>();

	public static String JAVA_OBJECT = "javaObject";
	
	static {
		listItems = new String[]{"txt1.txt", "txt2.txt", "button", "label", JAVA_OBJECT};
		
		data.put(listItems[0], "��ֻ��ʵ���˼򵥵Ŀؼ��Ϳؼ�֮�����ק�� ����������ʵ���������ܣ��ӵ��������߲���ϵͳ����༭������ק�������ǵ��������󲿷ֵ�����ʹ����TreeViewerչʾ��org.eclipse.jface.viewers.StructuredViewer#addDragSupport����ΪTreeViewer��Control�����DragSource���Ǵ���DragSource�Ĳ����Ѿ�Ԥ������ˡ�");
		data.put(listItems[1], "�й�Java������ - ������λ��SWT��List widget��ǰѡ�е���Ŀ");
		data.put(listItems[2], "control:button, text:hello");
		data.put(listItems[3], "control:label, text:���"); 
		data.put(listItems[4], new JavaObject(23, "Jack", 1));
	}
	
	public static Map<String, Object> getInitData(){
		return data;
	}
	
	public static String[] getListItems(){
		return listItems;
	}
}
