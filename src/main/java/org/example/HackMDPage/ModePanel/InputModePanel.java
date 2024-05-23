package org.example.HackMDPage.ModePanel;

import javax.swing.*;
import java.awt.*;

public class InputModePanel extends JPanel {
    private final JTextArea textArea;

    public InputModePanel(String content) {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setText(content);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public String getContent() {
        return textArea.getText();
    }

    public void insertContent(String content){
        textArea.insert(content, textArea.getCaretPosition());
    }
}
