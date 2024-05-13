package org.example.PaintPage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import org.example.PaintPage.PaintConstant.PaintObjectType;

public class TopBar extends JPanel{
    private final JButton dotButton;
    private final JButton rectangleButton;
    private PaintObjectType currentType = PaintObjectType.DOT;
    private JLabel previewColorLabel;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private JScrollBar redScrollBar;
    private JScrollBar greenScrollBar;
    private JScrollBar blueScrollBar;
    public TopBar(){
        dotButton = new JButton("Dot");
        rectangleButton = new JButton("Rectangle");
        add(dotButton);
        add(rectangleButton);
        addColorScrollBar();
        ButtonListener buttonListener = new ButtonListener();
        dotButton.addActionListener(buttonListener);
        rectangleButton.addActionListener(buttonListener);
    }
    private void addColorScrollBar(){
        JPanel colorPanel = new JPanel();
        JPanel barPanel = new JPanel(new GridLayout(0, 1));
        redScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
        greenScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
        blueScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
        JLabel redLabel = new JLabel("Red");
        JLabel greenLabel = new JLabel("Green");
        JLabel blueLabel = new JLabel("Blue");
        ColorAdjustmentListener colorAdjustmentListener = new ColorAdjustmentListener();
        redScrollBar.addAdjustmentListener(colorAdjustmentListener);
        greenScrollBar.addAdjustmentListener(colorAdjustmentListener);
        blueScrollBar.addAdjustmentListener(colorAdjustmentListener);
        barPanel.add(redLabel);
        barPanel.add(redScrollBar);
        barPanel.add(greenLabel);
        barPanel.add(greenScrollBar);
        barPanel.add(blueLabel);
        barPanel.add(blueScrollBar);
        colorPanel.add(barPanel);
        previewColorLabel = new JLabel(" ");
        previewColorLabel.setOpaque(true);
        previewColorLabel.setPreferredSize(new Dimension(50, 50));
        previewColorLabel.setBackground(Color.BLACK);
        colorPanel.add(previewColorLabel);
        add(colorPanel);
    }
    public PaintObjectType getCurrentType(){
        return currentType;
    }
    public Color getCurrentColor(){
        return new Color(red, green, blue);
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
    private class ColorAdjustmentListener implements AdjustmentListener {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            JScrollBar source = (JScrollBar)e.getSource();
            if(source == redScrollBar){
                red = source.getValue();
            }else if(source == greenScrollBar){
                green = source.getValue();
            }else if(source == blueScrollBar){
                blue = source.getValue();
            }
            previewColorLabel.setBackground(new Color(red, green, blue));
        }
    }
}
