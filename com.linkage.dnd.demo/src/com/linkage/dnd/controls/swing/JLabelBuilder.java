package com.linkage.dnd.controls.swing;

import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.linkage.dnd.controls.ComponentMeta;

public class JLabelBuilder extends AbstractBuilder {
 
	@Override
	public JComponent make(ComponentMeta metaData) {
		JLabel generated = new JLabel();
		generated.setBounds(0,0, 80, 29);
		generated.setText(metaData.getText());
		generated.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		generated.addMouseListener(listener);
		generated.addMouseMotionListener(listener);
		return generated;
	}

}
