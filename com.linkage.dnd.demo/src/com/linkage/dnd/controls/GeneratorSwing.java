package com.linkage.dnd.controls;

import javax.swing.JComponent;

import com.linkage.dnd.controls.swing.factory.ComponentFactory;

public class GeneratorSwing implements Generator<JComponent> {
	
	private GeneratorSwing(){ 
	}
	
	private static Generator<JComponent> instance = new GeneratorSwing();
	
	public static Generator<JComponent> getInstance(){
		return instance; 
	}
	
	public JComponent generate(ComponentMeta c){
		return ComponentFactory.getInstance().creator(c.getType()).make(c);
		
		/*
		JComponent component = null;
		if(c.getType() == ComponentType.BUTTON){
			JButton generated = new JButton();
			generated.setBounds(0,0, 80, 29);
			generated.setText(c.getText());
			generated.addMouseListener(listener);
			generated.addMouseMotionListener(listener);
			component = generated;
		}else if(c.getType() == ComponentType.LABEL){
			JLabel generated = new JLabel();
			generated.setBounds(0,0, 80, 29);
			generated.setText(c.getText());
			generated.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
			generated.addMouseListener(listener);
			generated.addMouseMotionListener(listener);
			component = generated;
		}
		return component;*/ 
	}
}
