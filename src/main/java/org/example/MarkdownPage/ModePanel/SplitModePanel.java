package org.example.MarkdownPage.ModePanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.example.base.BaseScrollBar;

public class SplitModePanel extends ModePanel {

    // private final JSplitPane splitPane;

    public SplitModePanel(String content) {
        setLayout(new GridLayout(1, 2));
        JScrollPane inputScrollPane = new JScrollPane(input);
        JScrollPane displayScrollPane = new JScrollPane(display);
        display.setBackground(new Color(230, 230, 230));
        inputScrollPane.setBorder(BorderFactory.createEmptyBorder());
        displayScrollPane.setBorder(BorderFactory.createEmptyBorder());
        inputScrollPane.setVerticalScrollBar(new BaseScrollBar(JScrollBar.VERTICAL));
        displayScrollPane.setVerticalScrollBar(new BaseScrollBar(JScrollBar.VERTICAL));
        // splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  new JScrollPane(input), new JScrollPane(display));
        // splitPane.setResizeWeight(0.5);

        input.getDocument().addDocumentListener(new MyDocumentListener());
        input.setText(content);
        input.setLineWrap(true);
        // add(splitPane);
        add(inputScrollPane);
        add(displayScrollPane);
    }

    private class MyDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            render(input.getText());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            render(input.getText());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            render(input.getText());
        }
    }

}
