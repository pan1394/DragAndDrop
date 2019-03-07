package com.linkage.dnd.demo;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class DNDExample3 {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI();
			}
		});
	}

	public static void createGUI() {
		JFrame jf = new JFrame("��ק �ļ�/�ı�/ͼƬ ��ʾ");
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		// ����һ���ı�����, ���ڽ�����קĿ��, �����ı������������Ӧ����ק����
		JTextArea textArea = new JTextArea(20, 30);
		textArea.setLineWrap(true); // �Զ�����
		panel.add(new JScrollPane(textArea)); // ʹ�� JScrollPane ����, �������ݹ���ʱ֧�ֹ���

		// ������קĿ�������
		DropTargetListener listener = new DropTargetListenerImpl(textArea);

		// �� textArea ��ע����קĿ�������
		DropTarget dropTarget = new DropTarget(textArea,
				DnDConstants.ACTION_COPY_OR_MOVE, listener, true);

		// ���Ҫ�Ƴ�������, ���Ե����������
		// dropTarget.removeDropTargetListener(listener);

		jf.setContentPane(panel);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	/**
	 * ��קĿ�������ʵ��
	 */
	private static class DropTargetListenerImpl implements DropTargetListener {

		/** ������ʾ��ק������ */
		private JTextArea textArea;

		public DropTargetListenerImpl(JTextArea textArea) {
			this.textArea = textArea;
		}

		@Override
		public void dragEnter(DropTargetDragEvent dtde) {
			System.out.println("dragEnter: ��קĿ������������");
		}

		@Override
		public void dragOver(DropTargetDragEvent dtde) {
			System.out.println("dragOver: ��קĿ��������������ƶ�");
		}

		@Override
		public void dragExit(DropTargetEvent dte) {
			System.out.println("dragExit: ��קĿ���뿪�������");
		}

		@Override
		public void dropActionChanged(DropTargetDragEvent dtde) {
			System.out.println("dropActionChanged: ��ǰ drop �������޸�");
		}

		@Override
		public void drop(DropTargetDropEvent dtde) {
			// һ�������ֻ��Ҫ���Ĵ˷����Ļص�
			System.out.println("drop: ��קĿ��������������ͷ�");

			boolean isAccept = false;

			try {
				/*
				 * 1. �ļ�: �ж���קĿ���Ƿ�֧���ļ��б����ݣ�����ק���Ƿ����ļ����ļ���, ֧��ͬʱ��ק�����
				 */
				if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
					// ������קĿ������
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					isAccept = true;

					// ���ļ����ϵ���ʽ��ȡ����
					List<File> files = (List<File>) dtde.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);

					// ���ļ�·��������ı�����
					if (files != null && files.size() > 0) {
						StringBuilder filePaths = new StringBuilder();
						for (File file : files) {
							filePaths.append("�ļ�: " + file.getAbsolutePath()
									+ "\n");
						}
						textArea.append(filePaths.toString());
					}
				}

				/*
				 * 2. �ı�: �ж���קĿ���Ƿ�֧���ı����ݣ�����ק���Ƿ����ı�����, �����Ƿ�֧�����ı�����ʽ��ȡ��
				 */
				if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
					// ������קĿ������
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					isAccept = true;

					// ���ı�����ʽ��ȡ����
					String text = dtde.getTransferable()
							.getTransferData(DataFlavor.stringFlavor)
							.toString();

					// ������ı�����
					textArea.append("�ı�: " + text + "\n");
				}

				/*
				 * 3. ͼƬ: �ж���קĿ���Ƿ�֧��ͼƬ���ݡ�ע��: ��קͼƬ����ָ���ļ�����ʽ��קͼƬ�ļ�,
				 * ����ָ��קһ��������Ļ����ʾ�Ĳ���֧����ק��ͼƬ��������ҳ����ʾ��ͼƬ����
				 */
				if (dtde.isDataFlavorSupported(DataFlavor.imageFlavor)) {
					// ������קĿ������
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					isAccept = true;

					// ��ͼƬ����ʽ��ȡ����
					Image image = (Image) dtde.getTransferable()
							.getTransferData(DataFlavor.imageFlavor);

					// ��ȡ�� image �����, ���ԶԸ�ͼƬ������Ӧ�Ĳ���������: �������ʾ��ͼ�α任�����浽���صȣ�,
					// ����ֻ��ͼƬ�Ŀ��������ı�����
					textArea.append("ͼƬ: " + image.getWidth(null) + " * "
							+ image.getHeight(null) + "\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// ����˴���ק�������Ǳ����ܵ�, �����������ק��ɣ�������ܻῴ����קĿ�귵��ԭλ��, ����Ӿ�����Ϊ�ǲ�֧����ק�Ĵ���Ч����
			if (isAccept) {
				dtde.dropComplete(true);
			}
		}
	}

}
