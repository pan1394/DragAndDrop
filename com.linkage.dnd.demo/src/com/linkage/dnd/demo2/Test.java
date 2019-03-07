package com.linkage.dnd.demo2;

import java.awt.BorderLayout;  
import java.awt.Dimension;  
import java.awt.datatransfer.DataFlavor;  
import java.awt.datatransfer.Transferable;  
import java.awt.datatransfer.UnsupportedFlavorException;  
import java.awt.dnd.DnDConstants;  
import java.awt.dnd.DropTarget;  
import java.awt.dnd.DropTargetDragEvent;  
import java.awt.dnd.DropTargetDropEvent;  
import java.awt.dnd.DropTargetEvent;  
import java.awt.dnd.DropTargetListener;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;  
import java.io.File;  
import java.io.FileReader;  
import java.io.IOException;  
  
import javax.swing.BorderFactory;  
import javax.swing.JEditorPane;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.JSplitPane;  
import javax.swing.UIManager;  
import javax.swing.WindowConstants;  
import javax.swing.text.Document;  
import javax.swing.text.EditorKit;  
  
public class Test extends JFrame implements DropTargetListener {  
        private JEditorPane textPane = new JEditorPane();  
  
        public Test() {  
                super("Drag and Drop With Swing");  
  
                new DropTarget(textPane, DnDConstants.ACTION_COPY_OR_MOVE, this);  
  
                JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  
                                createTreePanel(), createTextPanel());  
  
                splitPane.setDividerLocation(250);  
                splitPane.setOneTouchExpandable(true);  
  
                getContentPane().add(splitPane, BorderLayout.CENTER);  
        }  
  
        public static void main(String args[]) {  
  
                try {  
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
                } catch (Exception e1) {  
                        e1.printStackTrace();  
                }  
                Test test = new Test();  
  
                test.setBounds(300, 300, 850, 350);  
                test.setVisible(true);  
                test.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
                test.addWindowListener(new WindowAdapter() {  
                        public void windowClosed(WindowEvent e) {  
                                System.exit(0);  
                        }  
                });  
        }  
  
        private JPanel createTreePanel() {  
                JPanel treePanel = new JPanel();  
                DragTree tree = new DragTree();  
  
                treePanel.setLayout(new BorderLayout());  
                treePanel.add(new JScrollPane(tree), BorderLayout.CENTER);  
                treePanel.setBorder(BorderFactory  
                                .createTitledBorder("Drag source for filenames"));  
  
                return treePanel;  
        }  
  
        private JPanel createTextPanel() {  
                JPanel textPanel = new JPanel();  
  
                textPanel.setLayout(new BorderLayout());  
                textPanel.add(new JScrollPane(textPane), BorderLayout.CENTER);  
                textPanel.setMinimumSize(new Dimension(375, 0));  
                textPanel.setBorder(BorderFactory  
                                .createTitledBorder("Drop target for filenames"));  
  
                return textPanel;  
        }  
  
        private void readFile(final String filename) {  
                try {  
                        textPane.setPage(new File(filename).toURL());  
                } catch (IOException e) {  
                        EditorKit kit = textPane.getEditorKit();  
                        Document document = textPane.getDocument();  
  
                        try {  
                                document.remove(0, document.getLength());  
                                kit.read(new FileReader(filename), document, 0);  
                        } catch (Exception ex) {  
                                ex.printStackTrace();  
                        }  
                }  
        }  
  
        public void drop(DropTargetDropEvent e) {  
                try {  
                        DataFlavor stringFlavor = DataFlavor.stringFlavor;  
                        Transferable tr = e.getTransferable();  
  
                        if (e.isDataFlavorSupported(stringFlavor)) {  
                                String filename = (String) tr.getTransferData(stringFlavor);  
                                if (filename.endsWith(".txt") || filename.endsWith(".java")  
                                                || filename.endsWith(".jsp")  
                                                || filename.endsWith(".html")  
                                                || filename.endsWith(".htm")) {  
                                        e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);  
                                        readFile(filename);  
                                        textPane.setCaretPosition(0);  
                                        e.dropComplete(true);  
                                } else {  
                                        e.rejectDrop();  
                                }  
                        } else {  
                                e.rejectDrop();  
                        }  
                } catch (IOException ioe) {  
                        ioe.printStackTrace();  
                } catch (UnsupportedFlavorException ufe) {  
                        ufe.printStackTrace();  
                }  
        }  
  
        public void dragEnter(DropTargetDragEvent e) {  
        }  
  
        public void dragExit(DropTargetEvent e) {  
        }  
  
        public void dragOver(DropTargetDragEvent e) {  
        }  
  
        public void dropActionChanged(DropTargetDragEvent e) {  
        }  
}  
