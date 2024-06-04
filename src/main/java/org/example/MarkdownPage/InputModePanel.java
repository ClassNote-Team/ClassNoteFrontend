package org.example.MarkdownPage;

import javax.swing.*;
import java.awt.*;

public class InputModePanel extends JPanel {
    private final JTextArea textArea;

    public InputModePanel(String content) {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setText(content);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public String getContent() {
        return textArea.getText();
    }
}
