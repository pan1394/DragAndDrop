package com.linkage.dnd.drop;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

import com.linkage.dnd.controls.ComponentMeta;
import com.linkage.dnd.controls.Generator;
import com.linkage.dnd.controls.GeneratorSwing;
import com.linkage.dnd.data.Constants;
import com.linkage.dnd.data.DummyData;
import com.linkage.dnd.data.JavaObject;
import com.linkage.dnd.parser.StringParser;
import com.linkage.dnd.transferable.ClipBoardUtils;
import com.linkage.dnd.transferable.ObjectDataFlavor;


public class DropListenerSwing extends DropTargetAdapter{

	private JComponent container;
	
	public DropListenerSwing(JComponent container) {
		this.container = container;
	}
	 

	@Override
	public void drop(DropTargetDropEvent dtde) {
		try {
			DataFlavor javaFlavor = new ObjectDataFlavor(JavaObject.class);
			
			if(dtde.isDataFlavorSupported(javaFlavor)){
				// 接收拖拽目标数据
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
				// 以文本的形式获取数据
				JavaObject obj = (JavaObject)dtde.getTransferable().getTransferData(javaFlavor); 
				System.out.println(obj);
			}
			else if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				// 接收拖拽目标数据
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
				// 以文本的形式获取数据
				String data = dtde.getTransferable().getTransferData(DataFlavor.stringFlavor).toString(); 
				System.out.println(data);
				if(data.equals(DummyData.JAVA_OBJECT)){
					JavaObject obj = (JavaObject) ClipBoardUtils.getClipboardObject(javaFlavor);
					System.out.println(String.format(Constants.TEMPLATE, obj, "Swing", ""));
					return;
				}
				
				if(container instanceof JTextComponent){
					((JTextComponent) container).setText("");
					((JTextComponent) container).setText(data);
					return;
				}
				
				ComponentMeta meta = StringParser.getInstance().parse(data);
				Generator<JComponent> generator = GeneratorSwing.getInstance(); 
				JComponent comp = generator.generate(meta);
				this.container.add(comp); 
				this.container.updateUI();
			}
			 
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
		
	} 
}
