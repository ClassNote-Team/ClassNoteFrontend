package org.example.MarkdownPage.ModePanel;

import javax.swing.*;

import org.example.base.BaseScrollBar;

import java.awt.*;

public class DisplayModePanel extends ModePanel {

    public DisplayModePanel(String content) {
        setBackground(Color.WHITE); // 設定背景顏色為白色
        setLayout(new BorderLayout());
        render(content);
        JScrollPane scrollPane = new JScrollPane(display); // 建立一個帶滾動條的滾動面板
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // 設定滾動面板的邊框
        scrollPane.setVerticalScrollBar(new BaseScrollBar(JScrollBar.VERTICAL)); // 設定滾動面板的垂直滾動條
        add(scrollPane, BorderLayout.CENTER); // 將滾動面板加入到面板中
    }

}
