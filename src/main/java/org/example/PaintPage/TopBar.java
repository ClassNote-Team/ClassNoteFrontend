package org.example.PaintPage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.example.PaintPage.PaintConstant.PaintObjectType;

public class TopBar extends JPanel{
    private final JButton dotButton;
    private final JButton rectangleButton;
    private PaintObjectType currentType = PaintObjectType.DOT;
    private final Color currentColor = Color.BLACK;
    public TopBar(){
        dotButton = new JButton("Dot");
        rectangleButton = new JButton("Rectangle");
        add(dotButton);
        add(rectangleButton);
        ButtonListener buttonListener = new ButtonListener();
        dotButton.addActionListener(buttonListener);
        rectangleButton.addActionListener(buttonListener);
    }
    public PaintObjectType getCurrentType(){
        return currentType;
    }
    public Color getCurrentColor(){
        return currentColor;
    }
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == dotButton){
                currentType = PaintObjectType.DOT;
            }else if(e.getSource() == rectangleButton){
                currentType = PaintObjectType.RECTANGLE;
            }
        }
    }
}
