package com.linkage.dnd.controls.swing;

import javax.swing.JComponent;

import com.linkage.dnd.controls.Builder;
import com.linkage.dnd.controls.ComponentMeta;
import com.linkage.dnd.move.MouseMoveListenerSwing;

public abstract class AbstractBuilder implements Builder<JComponent> {

	protected MouseMoveListenerSwing listener = new MouseMoveListenerSwing();
	
	protected ComponentMeta metaData;
	
	 
}
