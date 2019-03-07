package com.linkage.dnd.transferable;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ObjectSelection implements Transferable {
	
	private Object obj;
 
	public ObjectSelection(Object obj) {
		this.obj = obj;
	}
 
	@Override // 只支持text/plain和obj
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] flavors = new DataFlavor[2];
		String mimeType = DataFlavor.javaJVMLocalObjectMimeType + ";class=" + obj.getClass().getName();
		
		try { // 这里返回两种，一种是Object Java对象，另一种是text/plain
			flavors[0] = new DataFlavor(mimeType);
			flavors[1] = DataFlavor.stringFlavor;
			return flavors;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
 
	@Override // 只支持text/pain和obj两种
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		// return false;
		return flavor.equals(DataFlavor.stringFlavor) ||
				flavor.getPrimaryType().equals("application") &&
				flavor.getSubType().equals("x-java-jvm-local-objectref") &&
				flavor.getRepresentationClass().isAssignableFrom(obj.getClass());
	}
 
	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		// TODO Auto-generated method stub
		// return null;
		if (!isDataFlavorSupported(flavor)) { // 如果不是这两种则直接抛出异常
			throw new UnsupportedFlavorException(flavor);
		}
		if (flavor.equals(DataFlavor.stringFlavor)) { // 要么是text/plain
			return obj.toString();
		}
		return obj; // 要么就是obj
	}
 
}
 