package org.example.PaintPage;

import java.awt.BorderLayout;

import javax.swing.*;

public class PaintPage {

    public void createAndShowGUI(){
        JFrame frame = new JFrame("Paint Page");

        TopBar topBar = new TopBar();
        DrawPanel drawPanel = new DrawPanel(topBar);
        frame.add(topBar, BorderLayout.NORTH);
        frame.add(drawPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // 設定窗口的大小為 400x300 像素
        frame.setVisible(true);
    }
}
