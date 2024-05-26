package org.example.MathEditPage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.MathEditPage.MathPageConstant.PAGE_MODE;
import org.example.base.BaseToolBar;

public class MathToolBar extends BaseToolBar {

    private MathButton greekSymbolButton;
    private MathButton calculusButton;
    private MathButton MathSymbolButton;
    private PanelSwitchListener listener;

    public MathToolBar() {
        setBorderPainted(false);
    }

    public void createToolBar() {
        MathSymbolButton = new PanelSwitchButton();
        MathSymbolButton.createButton("+-\\times\\div", MathPageConstant.LATEX_FONT_SIZE);
        MathSymbolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(MathSymbolButton.getHeight());
                listener.onPageSwitch(PAGE_MODE.MATH);
            }
        });
        add(MathSymbolButton);
        addSeparator(new Dimension(0, 0));

        greekSymbolButton = new PanelSwitchButton();
        greekSymbolButton.createButton("\\alpha\\beta\\gamma\\delta", MathPageConstant.LATEX_FONT_SIZE);
        greekSymbolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action here
                System.out.println(greekSymbolButton.getHeight());
                listener.onPageSwitch(PAGE_MODE.GREEK);
            }
        });
        add(greekSymbolButton);
        addSeparator(new Dimension(0, 0));

        calculusButton = new PanelSwitchButton();
        calculusButton.createButton("\\int\\sum\\prod\\bigcup", MathPageConstant.LATEX_FONT_SIZE / 2);
        calculusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(calculusButton.getHeight());
                listener.onPageSwitch(PAGE_MODE.CALCULUS);
            }
        });
        add(calculusButton);


        setLayout(new GridLayout(1, 5));
        setFloatable(false);
        // setPreferredSize(new Dimension(width, getPreferredSize().height));

        validate();
        repaint();
    }

    public void setMathButtonListener(PanelSwitchListener listener) {
        this.listener = listener;
    }
}
