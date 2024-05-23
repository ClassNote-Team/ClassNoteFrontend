package org.example.MathEditPage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

import org.example.MathEditPage.MathPageConstant.PAGE_MODE;

public class MathToolBar extends JToolBar{

    private MathButton greekSymbolButton;
    private MathButton calculusButton;
    private MathButton MathSymbolButton;
    private PageSwitchListener listener;

    public void createToolBar() {
        MathSymbolButton = new MathButton();
        MathSymbolButton.createButton("+-\\times\\div", MathPageConstant.LATEX_FONT_SIZE);
        MathSymbolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(MathSymbolButton.getHeight());
                listener.onPageSwitch(PAGE_MODE.MATH);
            }
        });
        add(MathSymbolButton);

        greekSymbolButton = new MathButton();
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

        calculusButton = new MathButton();
        calculusButton.createButton("\\int\\sum\\prod\\bigcup", MathPageConstant.LATEX_FONT_SIZE / 2);
        calculusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(calculusButton.getHeight());
                listener.onPageSwitch(PAGE_MODE.CALCULUS);
            }
        });
        add(calculusButton);


        setLayout(new FlowLayout(FlowLayout.CENTER));
        setFloatable(false);
        // setPreferredSize(new Dimension(width, getPreferredSize().height));

        validate();
        repaint();
    }

    public void setMathButtonListener(PageSwitchListener listener) {
        this.listener = listener;
    }
}
