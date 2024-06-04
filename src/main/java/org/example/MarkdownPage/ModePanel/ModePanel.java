package org.example.MarkdownPage.ModePanel;

import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class ModePanel extends JPanel{

    protected final JTextArea input;
    protected final JEditorPane display;

    public ModePanel() {
        input = new JTextArea();
        display = new JEditorPane();
        Font font = new Font("Arial", Font.PLAIN, 14);
        input.setFont(font);
    }

    public String getContent() {
        return input.getText();
    }

    public void insertContent(String content){
        input.insert(content, input.getCaretPosition());
    }

    public void render(String content){
        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(document);
        display.setContentType("text/html");
        display.setText(html);
        display.setEditable(false);
    }
}
