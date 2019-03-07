package com.linkage.dnd.demo3;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Font;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.linkage.dnd.data.DummyData;
import com.linkage.dnd.drop.DropListenerSwt;
import com.linkage.dnd.transferable.ObjectSelection;

public class Test {

	public static void main(String[] args){ 
		JFrame frame = new JFrame();
		frame.setSize(800,800);
		Container cp = frame.getContentPane();
		frame.setVisible(true);
		frame.setLayout(null);
		JPanel swingPanel = new JPanel();
		swingPanel.setBounds(0,0,400,800);
		swingPanel.setLayout(null);
		frame.add(swingPanel);
		 
		ListModel jListModel =  new DefaultComboBoxModel(DummyData.getListItems());
		final JList jlst = new JList(); 
		jlst.setBounds(29, 32, 101, 138);
		swingPanel.add(jlst);
		jlst.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlst.setModel(jListModel);
		java.awt.dnd.DragSource listSourceSwing = java.awt.dnd.DragSource.getDefaultDragSource();
		listSourceSwing.createDefaultDragGestureRecognizer(jlst, DnDConstants.ACTION_MOVE, new DragGestureListener() {
			@Override
			public void dragGestureRecognized(DragGestureEvent e) {   
				String key = jlst.getSelectedValue().toString();
				System.out.println(key);
				System.out.println(key);
				Object o = DummyData.getInitData().get(key);
				if(key.equals("javaObject")){ 
					e.startDrag(null, new ObjectSelection(o));
				}else{
					e.startDrag(null, new StringSelection(o.toString())  // transferable  
							); // drag source listener 被拖拽组件在源组件中的事件  
				}
			} 
		});
		
		Canvas canvas = new Canvas();
		canvas.setBounds(400, 0, 400, 800);
		cp.add(canvas); 
		 
		Display display = new Display();
		Shell shell = SWT_AWT.new_Shell(display,canvas);
		shell.setSize(400,400);
		shell.setLayout(new FillLayout());
		 
		/*Button button = new Button(shell,SWT.PUSH);
		button.setText("button");
		
		Text text = new Text(shell, SWT.BORDER  | SWT.WRAP | SWT.MULTI);
		text.setSize(200, 200);
		DropTarget dropTarget = new DropTarget(text, DND.DROP_MOVE);
		dropTarget.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		dropTarget.addDropListener(new DropListenerSwt(text)); */
		
		Composite p = new Composite(shell, SWT.BORDER);
		p.setSize(200, 200); 
		DropTarget swtDropTarget = new DropTarget(p, DND.DROP_MOVE);
		swtDropTarget.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		swtDropTarget.addDropListener(new DropListenerSwt(p));
		//shell.pack();
		 
		while (!shell.isDisposed()) {
		  if (!display.readAndDispatch()){
		    display.sleep ();
		  }
		} 
	}
}
