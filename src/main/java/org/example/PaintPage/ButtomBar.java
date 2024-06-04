package org.example.PaintPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.example.MarkdownPage.InsertPaintPageImageButtonHandler;
import org.example.MathEditPage.MathPageConstant;
import org.example.base.BaseButton;

import static org.example.MathEditPage.Manager.LaTeXManager.generateRandomString;

public class ButtomBar extends JPanel{
    private final BaseButton clearButton;
    private final BaseButton saveButton;
    private final DrawPanel drawPanel;
    private final JFrame frame;
    private InsertPaintPageImageButtonHandler insertPaintPageImageButtonHandler;
    public ButtomBar(DrawPanel drawPanel, JFrame frame, InsertPaintPageImageButtonHandler insertPaintPageImageButtonHandler){
        clearButton = new BaseButton("Clear");
        saveButton = new BaseButton("Save");
        this.drawPanel = drawPanel;
        this.frame = frame;
        this.insertPaintPageImageButtonHandler = insertPaintPageImageButtonHandler;
        add(clearButton);
        add(saveButton);
        ButtonListener buttonListener = new ButtonListener();
        clearButton.addActionListener(buttonListener);
        saveButton.addActionListener(buttonListener);
    }
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == clearButton){
                drawPanel.clearDrawing();
            } else if(e.getSource() == saveButton){
                try{
                    String path = MathPageConstant.IMAGE_PATH + generateRandomString(11) + ".png";
                    insertPaintPageImageButtonHandler.onButtonPaintPageImagePressed(path);
                    drawPanel.saveImage(path);
                    frame.dispose();
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
