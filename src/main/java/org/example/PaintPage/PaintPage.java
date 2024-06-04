package org.example.PaintPage;

import java.awt.BorderLayout;

import javax.swing.*;

import org.example.MarkdownPage.InsertPaintPageImageButtonHandler;

public class PaintPage {

    public void createAndShowGUI(InsertPaintPageImageButtonHandler insertPaintPageImageButtonHandler){
        JFrame frame = new JFrame("Paint Page");

        TopBar topBar = new TopBar();
        DrawPanel drawPanel = new DrawPanel(topBar);
        ButtonBar buttomBar = new ButtonBar(drawPanel, frame, insertPaintPageImageButtonHandler);
        frame.add(topBar, BorderLayout.NORTH);
        frame.add(drawPanel, BorderLayout.CENTER);
        frame.add(buttomBar, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600); 
        frame.setVisible(true);
    }
}
