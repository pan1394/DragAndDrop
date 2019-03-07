package com.linkage.dnd.drop;

import java.awt.datatransfer.DataFlavor;

import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.linkage.dnd.controls.ComponentMeta;
import com.linkage.dnd.controls.GeneratorSwt;
import com.linkage.dnd.data.Constants;
import com.linkage.dnd.data.DummyData;
import com.linkage.dnd.data.JavaObject;
import com.linkage.dnd.parser.StringParser;
import com.linkage.dnd.transferable.ClipBoardUtils;
import com.linkage.dnd.transferable.ObjectDataFlavor;
import com.linkage.dnd.transferable.ObjectTransfer;

public class DropListenerSwt extends DropTargetAdapter {
	
	private Control container;
	
	public DropListenerSwt(Control container){
		this.container = container;
	}
	 
	public void drop(DropTargetEvent event) {
		try{
			DataFlavor javaFlavor = new ObjectDataFlavor(JavaObject.class);
			
			if(TextTransfer.getInstance().isSupportedType(event.currentDataType)){
				String data = ((String) event.data); 
				System.out.println("text:"+data);
				if(container instanceof Composite){
					if(data.equals(DummyData.JAVA_OBJECT)){
						JavaObject obj = (JavaObject) ClipBoardUtils.getClipboardObject(javaFlavor);
						System.out.println(String.format(Constants.TEMPLATE, obj, "SWT", "Composite"));
						return;
					}
					GeneratorSwt generator = (GeneratorSwt) GeneratorSwt.getInstance();
					generator.setParent((Composite)container);
					
					ComponentMeta meta = StringParser.getInstance().parse(data);
					meta.setxPos(event.x);
					meta.setyPos(event.y);
					
					generator.generate(meta); 
				}else if(container instanceof Text){
					if(data.equals(DummyData.JAVA_OBJECT)){
						JavaObject obj = (JavaObject) ClipBoardUtils.getClipboardObject(javaFlavor);
						System.out.println(String.format(Constants.TEMPLATE, obj, "SWT", "Text"));
						return;
					}
					((Text)container).setText(data);
				}
			}else if(ObjectTransfer.getInstance().isSupportedType(event.currentDataType)){
				//Object data = event.data;
				//System.out.println("javaObject:"+data);
			}
		}catch(Exception e){
			System.err.println(e.getLocalizedMessage());
		}
	}
}
