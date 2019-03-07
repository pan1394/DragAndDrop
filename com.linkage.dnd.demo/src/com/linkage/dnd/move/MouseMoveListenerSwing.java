package com.linkage.dnd.move;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMoveListenerSwing extends MouseAdapter implements MoveFlagListener {
	// ������x��yΪ������ʱ����Ļ��λ�ú��϶�ʱ���ڵ�λ��
	int newX, newY, oldX, oldY;
	
	// ����������Ϊ�����ǰ������
	int startX, startY;

	@Override
	public void mousePressed(MouseEvent e) {
		// ��Ϊ�õ��¼�Դ���
		Component cp = (Component) e.getSource();

		// �������µ�ʱ���¼�����ǰ����������굱ǰ����Ļ��λ��
		startX = cp.getX();
		startY = cp.getY();
		oldX = e.getXOnScreen();
		oldY = e.getYOnScreen();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Component cp = (Component) e.getSource();
		// �϶���ʱ���¼������
		newX = e.getXOnScreen();
		newY = e.getYOnScreen();

		// ����bounds,������ʱ��¼�������ʼ����������϶��ľ������
		cp.setBounds(startX + (newX - oldX), startY + (newY - oldY),
				cp.getWidth(), cp.getHeight());

	}

}
