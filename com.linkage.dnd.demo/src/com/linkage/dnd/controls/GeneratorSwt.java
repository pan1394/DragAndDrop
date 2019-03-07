package com.linkage.dnd.controls;

 
 
 

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.linkage.dnd.move.MouseMoveListenerSwt;
 

public class GeneratorSwt implements Generator<Control> {

	private MouseMoveListenerSwt listener = new MouseMoveListenerSwt();
	
	private Composite parent;
	
	private static Generator<Control> instance = new GeneratorSwt();
 
	private GeneratorSwt(){
		
	}
	
	public static Generator<Control> getInstance(){
		return instance; 
	}
	
	public void setParent(Composite parent) {
		this.parent = parent;
	}
	
	@Override
	public Control generate(ComponentMeta c) {
		if(parent == null){
			throw new IllegalArgumentException("∏∏»›∆˜ Ù–‘parentŒ¥≈‰÷√£°");
		}
		
		Control component = null;
		if(c.getType() == ComponentType.BUTTON){
			final Button generated = new Button(parent, SWT.NONE);
			generated.setBounds(0,0, 80, 29);
			generated.setText(c.getText());
			generated.addMouseListener(listener);
			generated.addMouseMoveListener(listener);
			generated.addSelectionListener(new SelectionAdapter() {
				             @Override
				             public void widgetSelected(SelectionEvent e) {
				                 MessageDialog.openInformation(null, "","ƒ„µ•ª˜¡À "+ generated.getText()+" ∞¥≈•");
				             }
			});
			component = generated;
		}else if(c.getType() == ComponentType.LABEL){
			Label generated = new Label(parent, SWT.NONE);
			generated.setBounds(0, 0, 80, 29);
			generated.setText(c.getText()); 
			generated.addMouseListener(listener);
			generated.addMouseMoveListener(listener);
			component = generated;
		}
		
		return component;
	}

}
