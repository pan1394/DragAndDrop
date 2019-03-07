package com.linkage.dnd.demo;
 
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JApplet;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.linkage.dnd.data.DummyData;
import com.linkage.dnd.drop.DropListenerSwing;
import com.linkage.dnd.drop.DropListenerSwt;
import com.linkage.dnd.transferable.ObjectSelection;

 

public class DNDExample2 {
	private static Text txtTarget;
  
	public static void main(String[] args) {
		new DNDExample2();
	}
	
	public DNDExample2(){
		Shell shell = new Shell();
		shell.setSize(401, 543);
		shell.setBackground(new Color(null, 200, 200, 200));
		shell.setLayout(null); 
		
		construct(shell);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(29, 335, 318, 2);
		
		constructTargetContainer(shell);
		
		 
		Display display = Display.getDefault();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
		
	}

	private void constructTargetContainer(Shell shell) {
		Composite swtContainer = new Composite(shell, SWT.NONE);
		swtContainer.setBounds(175, 351, 172, 144);
		swtContainer.setLayout(null);
		DropTarget swtDropTarget = new DropTarget(swtContainer, DND.DROP_MOVE);
		swtDropTarget.setTransfer(new Transfer[] { /*ObjectTransfer.getInstance(),*/TextTransfer.getInstance() });
		swtDropTarget.addDropListener(new DropListenerSwt(swtContainer));
		
		
		Composite swingContainer = new Composite(shell, SWT.EMBEDDED);
		swingContainer.setBounds(26, 351, 112, 144); 
		Frame frame = SWT_AWT.new_Frame(swingContainer); 
		JPanel panel = new JPanel();
		panel.setBounds(26, 351, 64, 64);
		frame.add(panel);
		panel.setLayout(null); 
	    new java.awt.dnd.DropTarget(panel, DnDConstants.ACTION_COPY_OR_MOVE, new DropListenerSwing(panel), true);
	}
	
	private void construct(Shell shell){
		 
  
		final List list = new List(shell, SWT.BORDER); 
		list.setBounds(29, 32, 97, 98);
		list.setItems(DummyData.getListItems());
		DragSource listSource = new DragSource(list, DND.DROP_MOVE);
		listSource.setTransfer(new Transfer[] { /* ObjectTransfer.getInstance(), */TextTransfer.getInstance()}); 
		listSource.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
				// Set the data to be the first selected item's text
				String key = list.getSelection()[0];
				event.data = DummyData.getInitData().get(key);
				if(key.equals(DummyData.JAVA_OBJECT)){
					event.data = key;
					 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				        // 封装文本内容
				     Transferable trans = new ObjectSelection(DummyData.getInitData().get(key));
				        // 把文本内容设置到系统剪贴板
				     clipboard.setContents(trans, null); 
				}
			}
		});
		
		
		Composite composite = new Composite(shell, SWT.EMBEDDED);
		composite.setBounds(29, 217, 318, 100);
		Frame frame = SWT_AWT.new_Frame(composite);
		frame.setBackground(SystemColor.scrollbar);
		JApplet applet = new JApplet();
		applet.setBackground(SystemColor.scrollbar);
		frame.add(applet);
		
		ListModel jListModel =  new DefaultComboBoxModel(DummyData.getListItems());
		applet.setLayout(null);
		final JList jlst = new JList(); 
		jlst.setBounds(0, 0, 100, 100);
		applet.add(jlst);
		jlst.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlst.setModel(jListModel);
		java.awt.dnd.DragSource listSourceSwing = java.awt.dnd.DragSource.getDefaultDragSource();
		listSourceSwing.createDefaultDragGestureRecognizer(jlst, DnDConstants.ACTION_MOVE, new DragGestureListener() {
			@Override
			public void dragGestureRecognized(DragGestureEvent e) {   
				String key = jlst.getSelectedValue().toString();
				System.out.println(key);
				Object o = DummyData.getInitData().get(key);
				if(key.equals(DummyData.JAVA_OBJECT)){ 
					//e.startDrag(null, new ObjectSelection(o));
					e.startDrag(null, new StringSelection(key)) ;
					 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				     Transferable trans = new ObjectSelection(o);
				     clipboard.setContents(trans, null); 
				}else{
					e.startDrag(null, new StringSelection(o.toString())  // transferable  
							); // drag source listener 被拖拽组件在源组件中的事件  
				}
			} 
		});

		final JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(149, 0, 169, 100);
		applet.add(textArea); 
		// 在 textArea 上注册拖拽目标监听器
		new java.awt.dnd.DropTarget(textArea, DnDConstants.ACTION_COPY_OR_MOVE, new DropListenerSwing(textArea), true);
		
		txtTarget = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
		txtTarget.setBounds(175, 32, 172, 98);
		DropTarget dropTarget = new DropTarget(txtTarget, DND.DROP_MOVE);
		dropTarget.setTransfer(new Transfer[] { TextTransfer.getInstance()  });
		dropTarget.addDropListener(new DropListenerSwt(txtTarget)); 
		
		Label lblSwtList = new Label(shell, SWT.NONE);
		lblSwtList.setBounds(29, 10, 61, 17);
		lblSwtList.setText("SWT List");
		
		Label lblSwtText = new Label(shell, SWT.NONE);
		lblSwtText.setBounds(175, 10, 61, 17);
		lblSwtText.setText("SWT Text");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(29, 192, 61, 17);
		lblNewLabel.setText("Swing List");
		
		Label lblSwingText = new Label(shell, SWT.NONE);
		lblSwingText.setBounds(175, 194, 61, 17);
		lblSwingText.setText("Swing Text");
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(29, 168, 318, 2);
		
		shell.open();
	}
}
/*
class DropListener extends java.awt.dnd.DropTargetAdapter{
	private JTextArea textArea;
	
	public DropListener (JTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		try {
			if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				// 接收拖拽目标数据
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

				// 以文本的形式获取数据
				String text = dtde.getTransferable()
						.getTransferData(DataFlavor.stringFlavor)
						.toString();

				// 输出到文本区域
				textArea.setText("");
				textArea.append("文本: " + text + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	} 
}
 */