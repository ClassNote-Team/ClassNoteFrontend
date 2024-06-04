package org.example.PaintPage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;

import org.example.PaintPage.PaintConstant.PaintObjectType;
import org.example.base.BaseButton;

public class TopBar extends JPanel{
    private BaseButton dotButton;
    private BaseButton rectangleButton;
    private BaseButton ovalButton;
    private BaseButton lineButton;
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

        ImageIcon pen = new ImageIcon(new ImageIcon(PaintConstant.IMAGE_PATH + "pen.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        dotButton = new BaseButton(pen);
        dotButton.setPreferredSize(new Dimension(30,30));

        ImageIcon rectangle = new ImageIcon(new ImageIcon(PaintConstant.IMAGE_PATH + "rectangle.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        rectangleButton = new BaseButton(rectangle);
        rectangleButton.setPreferredSize(new Dimension(30,30));

        ImageIcon oval = new ImageIcon(new ImageIcon(PaintConstant.IMAGE_PATH + "oval.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ovalButton = new BaseButton(oval);
        ovalButton.setPreferredSize(new Dimension(30,30));

        ImageIcon line = new ImageIcon(new ImageIcon(PaintConstant.IMAGE_PATH + "line.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        lineButton = new BaseButton(line);
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
        redScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, PaintConstant.BAR_EXTEND, PaintConstant.COLOR_MIN, PaintConstant.COLOR_MAX);
        greenScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, PaintConstant.BAR_EXTEND, PaintConstant.COLOR_MIN, PaintConstant.COLOR_MAX);
        blueScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, PaintConstant.BAR_EXTEND, PaintConstant.COLOR_MIN, PaintConstant.COLOR_MAX);
        sizeScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, PaintConstant.DOT_SIZE, PaintConstant.BAR_EXTEND, 5, 50);
        redScrollBar.setPreferredSize(new Dimension(PaintConstant.BAR_WIDTH, PaintConstant.BAR_HEIGHT));
        greenScrollBar.setPreferredSize(new Dimension(PaintConstant.BAR_WIDTH, PaintConstant.BAR_HEIGHT));
        blueScrollBar.setPreferredSize(new Dimension(PaintConstant.BAR_WIDTH, PaintConstant.BAR_HEIGHT));
        sizeScrollBar.setPreferredSize(new Dimension(PaintConstant.BAR_WIDTH, PaintConstant.BAR_HEIGHT));
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
