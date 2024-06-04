package org.example.MarkdownPage.ModePanel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.example.MathEditPage.Manager.LaTeXManager;
import org.commonmark.Extension;
import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;


public class ModePanel extends JPanel{

    protected final JTextArea input;
    protected final JEditorPane display;

    public ModePanel() {
        input = new JTextArea();
        display = new JEditorPane();
        Font font = new Font("微軟正黑體", Font.PLAIN, 14);
        input.setFont(font);
    }

    public String getContent() {
        return input.getText();
    }

    public void insertContent(String content){
        input.insert(content, input.getCaretPosition());
        String text = input.getText();
        try {
            text = LaTeXManager.replaceToken(text);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        input.setText(text);
    }

    public void render(String content){
        ArrayList<Extension> extensions = new ArrayList<>();
        extensions.add(TablesExtension.create());
        extensions.add(AutolinkExtension.create());
        extensions.add(StrikethroughExtension.create());
        extensions.add(InsExtension.create());
        extensions.add(TaskListItemsExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        String html = renderer.render(document);
        display.setContentType("text/html");
        display.setText(html);
        display.setEditable(false);
    }
}
