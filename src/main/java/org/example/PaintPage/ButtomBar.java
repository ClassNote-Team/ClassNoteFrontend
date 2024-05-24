package org.example.PaintPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtomBar extends JPanel{
    private final JButton clearButton;
    private final JButton saveButton;
    private final DrawPanel drawPanel;
    public ButtomBar(DrawPanel drawPanel){
        clearButton = new JButton("Clear");
        saveButton = new JButton("Save");
        this.drawPanel = drawPanel;
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
                    drawPanel.saveImage();
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
