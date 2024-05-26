package org.example.PaintPage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;

import org.example.PaintPage.PaintConstant.PaintObjectType;

public class TopBar extends JPanel{
    private GridBagConstraints gbc;
    private JButton dotButton;
    private JButton rectangleButton;
    private JButton ovalButton;
    private JButton lineButton;
    private PaintObjectType currentType = PaintObjectType.DOT;
    private JLabel previewColorLabel;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int objectSize = 5;
    private JScrollBar redScrollBar;
    private JScrollBar greenScrollBar;
    private JScrollBar blueScrollBar;
    private JScrollBar sizeScrollBar;
    public TopBar(){
        setLayout(new GridLayout());
        addButtons();
        addColorScrollBar();
        
    }
    private void addButtons(){
        JPanel buttonPanel = new JPanel(new GridLayout(2,2));

        ImageIcon pen = new ImageIcon(new ImageIcon("src/main/java/org/example/PaintPage/img/pen.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        dotButton = new JButton(pen);
        dotButton.setPreferredSize(new Dimension(30,30));

        ImageIcon rectangle = new ImageIcon(new ImageIcon("src/main/java/org/example/PaintPage/img/rectangle.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        rectangleButton = new JButton(rectangle);
        rectangleButton.setPreferredSize(new Dimension(30,30));

        ImageIcon oval = new ImageIcon(new ImageIcon("src/main/java/org/example/PaintPage/img/oval.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ovalButton = new JButton(oval);
        ovalButton.setPreferredSize(new Dimension(30,30));

        ImageIcon line = new ImageIcon(new ImageIcon("src/main/java/org/example/PaintPage/img/line.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        lineButton = new JButton(line);
        lineButton.setPreferredSize(new Dimension(30,30));
        ButtonListener buttonListener = new ButtonListener();
        dotButton.addActionListener(buttonListener);
        rectangleButton.addActionListener(buttonListener);
        ovalButton.addActionListener(buttonListener);
        lineButton.addActionListener(buttonListener);
        buttonPanel.add(dotButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(ovalButton);
        buttonPanel.add(lineButton);

        add(buttonPanel);
    }
    private void addColorScrollBar(){
        JPanel colorPanel = new JPanel();
        JPanel barPanel = new JPanel(new GridLayout(0, 2));
        redScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
        greenScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
        blueScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
        sizeScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 5, 1, 5, 50);
        redScrollBar.setPreferredSize(new Dimension(100, 20));
        greenScrollBar.setPreferredSize(new Dimension(100, 20));
        blueScrollBar.setPreferredSize(new Dimension(100, 20));
        sizeScrollBar.setPreferredSize(new Dimension(100, 20));
        JLabel redLabel = new JLabel("Red");
        JLabel greenLabel = new JLabel("Green");
        JLabel blueLabel = new JLabel("Blue");
        JLabel sizeLabel = new JLabel("Size");
        ColorAdjustmentListener colorAdjustmentListener = new ColorAdjustmentListener();
        redScrollBar.addAdjustmentListener(colorAdjustmentListener);
        greenScrollBar.addAdjustmentListener(colorAdjustmentListener);
        blueScrollBar.addAdjustmentListener(colorAdjustmentListener);
        sizeScrollBar.addAdjustmentListener(colorAdjustmentListener);
        barPanel.add(redLabel);
        barPanel.add(redScrollBar);
        barPanel.add(greenLabel);
        barPanel.add(greenScrollBar);
        barPanel.add(blueLabel);
        barPanel.add(blueScrollBar);
        barPanel.add(sizeLabel);
        barPanel.add(sizeScrollBar);
        colorPanel.add(barPanel);
        previewColorLabel = new JLabel(" ");
        previewColorLabel.setOpaque(true);
        previewColorLabel.setPreferredSize(new Dimension(objectSize, objectSize));
        previewColorLabel.setBackground(new Color(red, green, blue));

        add(colorPanel);

        JPanel preview = new JPanel();
        preview.add(previewColorLabel);
        add(preview);
    }
    public PaintObjectType getCurrentType(){
        return currentType;
    }
    public Color getCurrentColor(){
        return new Color(red, green, blue);
    }
    public int getObjectSize(){
        return objectSize;
    }
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == dotButton){
                currentType = PaintObjectType.DOT;
            }else if(e.getSource() == rectangleButton){
                currentType = PaintObjectType.RECTANGLE;
            }else if(e.getSource() == ovalButton){
                currentType = PaintObjectType.OVAL;
            }else if(e.getSource() == lineButton){
                currentType = PaintObjectType.LINE;
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
            }else if(source == sizeScrollBar){
                objectSize = source.getValue();
            }
            previewColorLabel.setPreferredSize(new Dimension(objectSize, objectSize));
            previewColorLabel.setBackground(new Color(red, green, blue));
            previewColorLabel.revalidate();  // 通知容器需要重新佈局
            previewColorLabel.repaint();
        }
    }
}