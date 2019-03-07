package com.linkage.dnd.controls.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.linkage.dnd.controls.ComponentMeta;

public class JButtonBuilder extends AbstractBuilder{
 
	@Override
	public JComponent make(ComponentMeta metaData){
		final JButton generated = new JButton();
		generated.setBounds(0,0, 80, 29);
		generated.setText(metaData.getText());
		generated.addMouseListener(listener);
		generated.addMouseMotionListener(listener);
		generated.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(generated, "test");
				
			}
		});
		return generated;
	}
}
