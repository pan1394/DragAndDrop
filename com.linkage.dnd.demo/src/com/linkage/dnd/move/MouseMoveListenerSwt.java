package com.linkage.dnd.move;
 

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Control;

public class MouseMoveListenerSwt extends MouseAdapter implements MouseMoveListener, MoveFlagListener {
	
	// 这两组x和y为鼠标点下时在屏幕的位置和拖动时所在的位置
	int newX, newY, oldX, oldY;

	// 这两个坐标为组件当前的坐标
	int startX, startY;
	
	int downX, downY;
	boolean isDown;
 
	public void mouseDown(MouseEvent e) {
		isDown = true; 
		// 此为得到事件源组件
		Control cp = (Control) e.getSource();
		// 当鼠标点下的时候记录组件当前的坐标与鼠标当前在屏幕的位置
		startX = cp.getBounds().x;
		startY = cp.getBounds().y;
		oldX = downX = e.x; 
		oldY = downY = e.y;
	}

	public void mouseUp(MouseEvent e) {
		Control cp = (Control) e.getSource();
		if(isDown){ 
			newX = e.x;
			newY = e.y; 
			cp.setLocation(startX + (newX - oldX), startY + (newY - oldY));
		} 
	}

	@Override
	public void mouseMove(MouseEvent e) {
		/*Control cp = (Control) e.getSource();
		if(isDown){ 
			newX = e.x;
			newY = e.y; 
			cp.setLocation(startX + (newX - oldX), startY + (newY - oldY));
		} */
	}
}
