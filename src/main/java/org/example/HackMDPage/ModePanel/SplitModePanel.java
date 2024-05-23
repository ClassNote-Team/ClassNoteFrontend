package org.example.HackMDPage.ModePanel;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SplitModePanel extends ModePanel {

    private final JSplitPane splitPane;

    public SplitModePanel(String content) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  new JScrollPane(input), new JScrollPane(display));
        splitPane.setResizeWeight(0.5);

        input.getDocument().addDocumentListener(new MyDocumentListener());
        input.setText(content);
        input.setLineWrap(true);
        add(splitPane);
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
