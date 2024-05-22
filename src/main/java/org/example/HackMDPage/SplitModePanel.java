package org.example.HackMDPage;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SplitModePanel extends JPanel {

    private final JTextArea input = new JTextArea();
    private final JEditorPane display = new JEditorPane();
    private final JSplitPane splitPane;

    public SplitModePanel(String content) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  new JScrollPane(input), new JScrollPane(display));
        splitPane.setResizeWeight(0.5);

        display.setEditable(false);

        input.getDocument().addDocumentListener(new MyDocumentListener());
        input.setText(content);
        input.setLineWrap(true);
        add(splitPane);
    }

    class MyDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateDisplay();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateDisplay();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateDisplay();
        }
    }

    public String getContent() {
        return input.getText();
    }

    private void updateDisplay() {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(input.getText());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(document);
        display.setContentType("text/html");
        display.setText(html);
        System.out.println(html);
    }
}
