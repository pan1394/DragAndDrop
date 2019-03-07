package com.linkage.dnd.demo;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class PictureDnd {

	private JFrame mainFrame;
	private JPanel mainPanel;
	private PictureComponent[] pictures;

	public PictureDnd() {
		mainFrame = new JFrame();
		mainPanel = new JPanel(new GridLayout(2, 2));

		pictures = new PictureComponent[4];
		pictures[0] = new PictureComponent(new ImageIcon("C:\\Image\\1.jpeg").getImage());
		pictures[1] = new PictureComponent(new ImageIcon("C:\\Image\\2.jpeg").getImage());
		pictures[2] = new PictureComponent(null);
		pictures[3] = new PictureComponent(null);

		mainPanel.add(pictures[0]);
		mainPanel.add(pictures[1]);
		mainPanel.add(pictures[2]);
		mainPanel.add(pictures[3]);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(350, 400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new PictureDnd();
	}

	private class PictureComponent extends JComponent implements FocusListener,
			MouseListener, MouseMotionListener {

		private static final long serialVersionUID = 861556623335710551L;
		private Image image;
		
		public PictureComponent(Image image){
			this.image = image;
			setPreferredSize(new Dimension(125, 125));
			setFocusable(true);
			setTransferHandler(new PictureTransferHandler());
			addFocusListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		
		public Image getImage(){
			return image;
		}
		
		public void setImage(Image image){
			this.image = image;
			repaint();
		}
		
		// �������ǵ��࣬�򵥵������滭һ��ͼ
		public void paintComponent(Graphics graphics){
			Graphics g = graphics.create();
			g.setColor(Color.green); // ����ǰͼ�������ĵĵ�ǰ��ɫ���ó�green
			g.fillRect(0, 0, image != null ? image.getWidth(this) : 125, image != null ? image.getHeight(this) : 125); // ���ƾ���
			if(image != null){
				g.drawImage(image, 0, 0, this);
			}
			if(isFocusOwner()){
				g.setColor(Color.red);
			}else{
				g.setColor(Color.black);
			}
			// ���߿�����ǽ������ߣ��߿�Ϊ��ɫ������Ϊ��ɫ
			g.drawRect(0, 0, image != null ? image.getWidth(this) : 125, image != null ? image.getHeight(this) : 125);
			g.dispose(); // �ͷŴ�ͼ�ε������Ĳ��ͷ�����ʹ�õ�����ϵͳ��Դ��
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			repaint();
		}

		@Override
		public void focusLost(FocusEvent e) {
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			requestFocusInWindow();
		}

		@Override
		public void mouseEntered(MouseEvent e) { }

		@Override
		public void mouseExited(MouseEvent e) { }

		@Override
		public void mousePressed(MouseEvent e) { }

		@Override
		public void mouseReleased(MouseEvent e) { }

		@Override
		public void mouseDragged(MouseEvent e) {
			JComponent c = (JComponent)e.getSource();
			TransferHandler handler = c.getTransferHandler();
			// exportAsDrag
			handler.exportAsDrag(c, e, TransferHandler.COPY);
		}

		@Override
		public void mouseMoved(MouseEvent e) { }

	}
	
	// һ��������װ���ݵ���, ����ֻ������ͼƬ���͵����ݣ�
	private class TransferablePicture implements Transferable{
		
		/* DataFlavor.imageFlavor ��DataFlavor�����һ��DataFlavor����
		 ��ΪͼƬ���ã��������Դ����������ͣ����������Զ��壬�Ժ��ٿ���ζ���*/
		DataFlavor flavors[] = {DataFlavor.imageFlavor};
		Image image;
		
		public TransferablePicture(Image image){
			this.image = image;
		}
		
		@Override
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			// ���ݲ�����������
			if(flavor.equals(DataFlavor.imageFlavor)){
				return image;
			}
			return null;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return flavors;// ����Transferable������Щ��������
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			for(DataFlavor f: flavors){
				if(flavor.equals(f)){
					return true;
				}
			}
			return false;
//			return flavor.equals(DataFlavor.imageFlavor);
		}
		
	}
	
	@SuppressWarnings("serial")
	private class PictureTransferHandler extends TransferHandler{
		public Transferable createTransferable(JComponent c){
			PictureComponent pc = (PictureComponent)c;
			// ���ù��캯���԰�װ���ݣ�������Ҫ��װ�������Բ�����ʽ����
			return new TransferablePicture(pc.getImage());
		}
		
		// �ж��Ƿ���Ե�������
		public boolean canImport(JComponent c, DataFlavor[] flavors){
			for(DataFlavor flavor: flavors){
				if(flavor.equals(DataFlavor.imageFlavor)){
					return true;
				}
			}
			return false;
		}
		
		// ��������
		public boolean importData(JComponent c, Transferable t){
			if(canImport(c, t.getTransferDataFlavors())){
				PictureComponent pc = (PictureComponent)c;
				try {
					Image image = (Image) t.getTransferData(DataFlavor.imageFlavor);
					pc.setImage(image);
					return true;
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
		
		public void exportDone(JComponent c, Transferable data, int action){
			// ���������Ժ�Ҫ�ж����ƶ����Ǹ��ӣ�Ȼ���پ����Ƿ�Ҫɾ���Ϸ�Դ������
			PictureComponent picture = (PictureComponent) c;
			if(action == MOVE){
				picture.setImage(null);
			}
		}
		
		public int getSourceActions(JComponent c){
			return COPY_OR_MOVE;
		}
		
	}
	
	
}

