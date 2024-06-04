package org.example.MathEditPage;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.example.MarkdownPage.InsertButtonHandler;
import org.example.base.BaseButton;
import org.example.base.BaseScrollBar;
import org.example.base.BaseToolBar;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MathEditPage implements MathButtonHandler {

    private JFrame frame;
    private JTextArea latexArea;
    private JPanel editPanel;
    private JScrollPane preview;
    private JScrollPane latexContent;
    private MathKeyboard keyboard;
    private LaTexPanel previewContent;
    private BaseToolBar actionToolBar;
    private BaseButton insertButton;
    private BaseButton cleanButton;
    private InsertButtonHandler handler;

    public void createAndShowGUI(){
        frame = new JFrame("Math Keyboard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600); // 設定窗口的大小為 400x300 像素
        frame.setLayout(new GridLayout(1, 2));

        setInputAndPreview();

        createSplitPane();

        frame.setVisible(true);
    }

    private void setInputArea() {
        latexContent = new JScrollPane(latexArea);
        latexContent.setBorder(BorderFactory.createEmptyBorder());
        latexContent.setVerticalScrollBar(new BaseScrollBar(JScrollBar.VERTICAL));
        keyboard = new MathKeyboard();
        keyboard.createKeyboard();
        keyboard.setMathButtonListener(this);

        editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(2, 1));
        editPanel.add(latexContent);
        editPanel.add(keyboard);
    }

    private void setPreviewArea() {
        previewContent = new LaTexPanel();
        previewContent.setLayout(new BorderLayout());
        actionToolBar = new BaseToolBar();
        insertButton = new BaseButton("Insert");
        cleanButton = new BaseButton("Clean");

        insertButton.addActionListener(e -> {
            handler.onButtonPressed(latexArea.getText());
            frame.dispose();
        });
        cleanButton.addActionListener(e -> {
            frame.dispose();
        });

        actionToolBar.setLayout(new BorderLayout());

        actionToolBar.add(cleanButton, BorderLayout.WEST);
        actionToolBar.add(insertButton, BorderLayout.EAST);

        previewContent.add(actionToolBar, BorderLayout.SOUTH);
        preview = new JScrollPane(previewContent);
        preview.setBorder(BorderFactory.createEmptyBorder());
        preview.setVerticalScrollBar(new BaseScrollBar(JScrollBar.VERTICAL));
    }

    private void setInputAndPreview() {
        DocumentHandler documentHandler = new DocumentHandler();

        latexArea = new JTextArea();
        latexArea.setLineWrap(true);  // 啟用自動換行
        latexArea.getDocument().addDocumentListener(documentHandler);

        setInputArea();
        setPreviewArea();
    }

    private void createSplitPane() {
        frame.add(editPanel);
        frame.add(preview);

        frame.revalidate(); // Revalidate to update the frame
        frame.repaint(); // Repaint to update the frame
    }

    private String fixLatexString(String latex) {
        boolean isLeft = false;
        String fixedLatex = "";
        for (char c : latex.toCharArray()) {
            if (c == '{') {
                isLeft = true;
                fixedLatex += c;
            }
            else if (c == '}') {
                isLeft = false;
            }
            if (isLeft == false) fixedLatex += c;
        }
        return fixedLatex;
    }

    public void setInsertHandler(InsertButtonHandler handler){
        this.handler = handler;
    }

    @Override
    public void onMathButtonPressed(String latex) {
        if(!latex.contains("{bmatrix}") && !latex.contains("{pmatrix}") && !latex.contains("{vmatrix}")) latex = fixLatexString(latex);
        latexArea.insert(latex, latexArea.getCaretPosition());
    }

    private class DocumentHandler implements DocumentListener {
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
            System.out.println("Text style or attributes changed");
        }
    }
}