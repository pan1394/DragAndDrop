package com.linkage.dnd.move;
 

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Control;

public class MouseMoveListenerSwt extends MouseAdapter implements MouseMoveListener, MoveFlagListener {
	
	// ������x��yΪ������ʱ����Ļ��λ�ú��϶�ʱ���ڵ�λ��
	int newX, newY, oldX, oldY;

	// ����������Ϊ�����ǰ������
	int startX, startY;
	
	int downX, downY;
	boolean isDown;
 
	public void mouseDown(MouseEvent e) {
		isDown = true; 
		// ��Ϊ�õ��¼�Դ���
		Control cp = (Control) e.getSource();
		// �������µ�ʱ���¼�����ǰ����������굱ǰ����Ļ��λ��
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
