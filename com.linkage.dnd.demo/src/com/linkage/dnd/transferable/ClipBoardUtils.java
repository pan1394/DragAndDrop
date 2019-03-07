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
     * 把文本设置到剪贴板（复制）
     */
    public static void setClipboard(Object obj) {
        // 获取系统剪贴板
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
     * 从剪贴板中获取文本（粘贴）
     * @throws ClassNotFoundException 
     */
    public static Object getClipboardObject(DataFlavor flavor) {
    	
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);

        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(flavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    return trans.getTransferData(flavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

} 