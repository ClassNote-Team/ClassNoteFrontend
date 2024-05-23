package org.example.MathEditPage.SymbolPanel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.example.MathEditPage.MathButton;
import org.example.MathEditPage.MathButtonHandler;
import org.example.MathEditPage.MathPageConstant;

public abstract class SymbolPanel extends JPanel {
    private int preferredSize;
    private MathButtonHandler listener;
    protected ArrayList<MathButton> buttons;

    public SymbolPanel(){
        buttons = new ArrayList<MathButton>();
        preferredSize = 0;
    }

    public void createSymbolPanel() {
        setButton();

        for (MathButton button : buttons) {
            preferredSize = Math.max(preferredSize, button.getPreferredSize().width);
            preferredSize = Math.max(preferredSize, button.getPreferredSize().height);
        }
        
        setLayout(new GridLayout(MathPageConstant.GRIDLAYOUT_ROWS, 0));
        
        for (MathButton button : buttons) {
            add(button);
        }

        setButtonAction();

        setMaximumSize(getPreferredSize());

        revalidate();
        repaint();
    }
    
    public int getButtonPreferredSize() {
        return preferredSize;
    }

    public int getButtonCount() {
        return buttons.size();
    }

    public void setMathButtonListener(MathButtonHandler listener) {
        this.listener = listener;
    }

    public void setButtonAction() {
        for (MathButton button : buttons) {
            button.addActionListener(e -> {
                listener.onMathButtonPressed(button.getLatex());
            });
        }
    }

    protected abstract void setButton();
}
