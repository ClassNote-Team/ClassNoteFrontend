package org.example.MathEditPage;

import org.example.MathEditPage.LaTexPanel;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;

public class MathEditPage {

    private JFrame frame;
    private JTextArea latexArea;
    private JSplitPane splitPane;
    private JSplitPane editPane;
    private JScrollPane preview;
    private JScrollPane latexContent;
    private JTextArea previewArea;
    private MathKeyboard keyboard;
    private LaTexPanel previewContent;

    public void createAndShowGUI(){
        frame = new JFrame("JFrame with TextArea");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // 設定窗口的大小為 400x300 像素

        setInputAndPreview();

        createSplitPane();

        frame.setVisible(true);
    }

    private void setInputAndPreview() {
        DocumentChanged documentChanged = new DocumentChanged();

        latexArea = new JTextArea();
        latexArea.setLineWrap(true);  // 啟用自動換行
        latexArea.getDocument().addDocumentListener(documentChanged);

        previewArea = new JTextArea();
        previewArea.setEditable(false);
        previewArea.setLineWrap(true);  // 啟用自動換行
    }

    private void createSplitPane() {
        // Create JScrollPane
        latexContent = new JScrollPane(latexArea);
        keyboard = new MathKeyboard();
        keyboard.createKeyboard(frame.getWidth() / 2, frame.getHeight() / 3 * 2);

        previewContent = new LaTexPanel();
        preview = new JScrollPane(previewContent);
        editPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, latexContent, keyboard);
        editPane.setDividerLocation(frame.getHeight() / 3);
        editPane.setResizeWeight(0.3);
        editPane.setEnabled(false);

        // Create JSplitPane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editPane, preview);
        splitPane.setDividerLocation(frame.getWidth() / 2);
        splitPane.setResizeWeight(0.5);
        splitPane.setEnabled(false);

        frame.add(splitPane, BorderLayout.CENTER);

        frame.revalidate(); // Revalidate to update the frame
        frame.repaint(); // Repaint to update the frame
    }

    private class DocumentChanged implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            if (e.getDocument() == latexArea.getDocument()){
                previewContent.setText(latexArea.getText());
                previewContent.repaint();
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (e.getDocument() == latexArea.getDocument()){
                previewContent.setText(latexArea.getText());
                previewContent.repaint();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // This method is called when the style or attributes of the text are changed.
            System.out.println("Text style or attributes changed");
        }
    }
}