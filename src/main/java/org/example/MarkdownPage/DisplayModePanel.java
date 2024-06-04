package org.example.MarkdownPage;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import org.example.MathEditPage.Manager.LaTeXManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DisplayModePanel extends JPanel {

    private final JEditorPane display = new JEditorPane();

    public DisplayModePanel(String content) throws IOException {
        setBackground(Color.WHITE); // 設定背景顏色為白色
        setLayout(new BorderLayout());
        System.out.println("qwer");
        content = LaTeXManager.replaceToken(content);
        setContent(content);
        JScrollPane scrollPane = new JScrollPane(display); // 建立一個帶滾動條的滾動面板
        add(scrollPane, BorderLayout.CENTER); // 將滾動面板加入到面板中
    }

    public void setContent(String content) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(document);
        display.setContentType("text/html"); // 設定顯示內容的類型為HTML
        display.setText(html);
        display.setEditable(false); // 設定編輯功能為關閉
    }
}