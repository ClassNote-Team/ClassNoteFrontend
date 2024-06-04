package org.example.MarkdownPage.ModePanel;

import javax.swing.*;

import org.example.base.BaseScrollBar;

import java.awt.*;

public class InputModePanel extends ModePanel {

    public InputModePanel(String content) {
        setLayout(new BorderLayout());
        input.setText(content);
        input.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(input);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBar(new BaseScrollBar(JScrollBar.VERTICAL));
        add(scrollPane, BorderLayout.CENTER);
    }

}
