package com.linkage.dnd.transferable;

import java.awt.datatransfer.DataFlavor;

public class ObjectDataFlavor extends DataFlavor {

	public ObjectDataFlavor(Class clazz) throws ClassNotFoundException{
		super(DataFlavor.javaJVMLocalObjectMimeType + ";class=" + clazz.getName()); 
	}
}
