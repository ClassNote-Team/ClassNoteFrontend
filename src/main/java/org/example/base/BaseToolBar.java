package org.example.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JToolBar;

public class BaseToolBar extends JToolBar{
    private int margin = 5;

    public BaseToolBar(){
        super();
        setFloatable(false);
        setMargin(new Insets(margin, margin, margin, margin));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // 設定背景顏色
        setBackground(Color.DARK_GRAY);
    }
}
