package com.linkage.dnd.demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class DNDExample {

	public static void main(String[] args) {
		Shell shell = new Shell();
		shell.setBackground(new Color(null, 200, 200, 200));
		shell.setLayout(new GridLayout(2, false));

		// Create the tree and some tree items
		final Tree tree = new Tree(shell, SWT.NONE);
		TreeItem item1 = new TreeItem(tree, SWT.NONE);
		item1.setText("Item 1");
		TreeItem item2 = new TreeItem(tree, SWT.NONE);
		item2.setText("Item 2");

		// Create the drag source on the tree
		DragSource ds = new DragSource(tree, DND.DROP_MOVE);
		ds.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		ds.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
				// Set the data to be the first selected item's text
				event.data = tree.getSelection()[0].getText();
			}
		});

		// Create the text field
		final Text text = new Text(shell, SWT.NONE);

		// Create the drop target on the text field
		DropTarget dt = new DropTarget(text, DND.DROP_MOVE);
		dt.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		dt.addDropListener(new DropTargetAdapter() {
			public void drop(DropTargetEvent event) {
				// Set the text field's text to the text being dropped
				text.setText((String) event.data);
			}
		});

		
		
		shell.pack();
		shell.open();
		Display display = Display.getDefault();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}
}