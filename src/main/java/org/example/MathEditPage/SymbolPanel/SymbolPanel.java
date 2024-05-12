package org.example.MathEditPage.SymbolPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.example.MathEditPage.MathButton;

public abstract class SymbolPanel extends JPanel {
    protected ArrayList<MathButton> buttons;

    public SymbolPanel(){
        // super(new FlowLayout(FlowLayout.LEFT));
        buttons = new ArrayList<MathButton>();
    }

    public void createSymbolPanel(int width, int height) {
        setButton();

        int preferredSize = 0;
        for (MathButton button : buttons) {
            preferredSize = Math.max(preferredSize, button.getPreferredSize().width);
            preferredSize = Math.max(preferredSize, button.getPreferredSize().height);
        }
        int cols = width / preferredSize;
        // int rows = buttons.size() / cols;
        // if (rows * cols < buttons.size()) {
        //     rows++;
        // }
        setLayout(new GridLayout(0, cols));
        for (MathButton button : buttons) {
            button.setMaximumSize(new Dimension(preferredSize, preferredSize));
            button.setMinimumSize(new Dimension(preferredSize, preferredSize));
            button.setPreferredSize(new Dimension(preferredSize, preferredSize));
            add(button);
            // button.setMargin(new Insets(0, 0, 0, 0));
            // System.out.println(button.getInsets());
        }

        // System.out.println(cols);
        // System.out.println(getPreferredSize());
        // System.out.println(preferredSize);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setPreferredSize(new Dimension(width, getPreferredSize().height));

        revalidate();
        repaint();
    }

    protected abstract void setButton();
}
