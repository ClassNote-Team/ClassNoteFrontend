package org.example.PaintPage;

import java.awt.BorderLayout;

import javax.swing.*;

public class PaintPage {

    public void createAndShowGUI(){
        JFrame frame = new JFrame("Paint Page");

        TopBar topBar = new TopBar();
        DrawPanel drawPanel = new DrawPanel(topBar);
        ButtomBar buttomBar = new ButtomBar(drawPanel, frame);
        frame.add(topBar, BorderLayout.NORTH);
        frame.add(drawPanel, BorderLayout.CENTER);
        frame.add(buttomBar, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); 
        frame.setVisible(true);
    }
}
