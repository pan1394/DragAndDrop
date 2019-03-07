package com.linkage.dnd.transferable;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import com.linkage.dnd.data.JavaObject;

public class ClipBoardUtils {

    public static void main(String[] args) throws Exception {
        
    	setClipboard(new JavaObject(13, "asdff", 1));
    	JavaObject o = (JavaObject) getClipboardObject(new ObjectDataFlavor(JavaObject.class));
        System.out.println(o);
        clearClipboard();
    }

    /**
     * ���ı����õ������壨���ƣ�
     */
    public static void setClipboard(Object obj) {
        // ��ȡϵͳ������
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        if(obj == null){
        	clearClipboard();
        }else{
        	Transferable trans = new ObjectSelection(obj);
        	clipboard.setContents(trans, null);
        }
    }

    public static void clearClipboard(){
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(new StringSelection(""), null);
    }
    /**
     * �Ӽ������л�ȡ�ı���ճ����
     * @throws ClassNotFoundException 
     */
    public static Object getClipboardObject(DataFlavor flavor) {
    	
        // ��ȡϵͳ������
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // ��ȡ�������е�����
        Transferable trans = clipboard.getContents(null);

        if (trans != null) {
            // �жϼ������е������Ƿ�֧���ı�
            if (trans.isDataFlavorSupported(flavor)) {
                try {
                    // ��ȡ�������е��ı�����
                    return trans.getTransferData(flavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

} 