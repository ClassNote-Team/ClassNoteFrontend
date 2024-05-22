package org.example.MathEditPage.SymbolPanel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.example.MathEditPage.MathButton;
import org.example.MathEditPage.MathButtonListener;
import org.example.MathEditPage.MathPageConstant;

public abstract class SymbolPanel extends JPanel {
    private int preferredSize;
    private MathButtonListener listener;
    protected ArrayList<MathButton> buttons;

    public SymbolPanel(){
        // super(new FlowLayout(FlowLayout.LEFT));
        buttons = new ArrayList<MathButton>();
        preferredSize = 0;
    }

    public void createSymbolPanel() {
        setButton();

        for (MathButton button : buttons) {
            preferredSize = Math.max(preferredSize, button.getPreferredSize().width);
            preferredSize = Math.max(preferredSize, button.getPreferredSize().height);
        }
        // int cols = width / preferredSize;
        // int rows = buttons.size() / cols;
        // if (rows * cols < buttons.size()) {
        //     rows++;
        // }
        setLayout(new GridLayout(MathPageConstant.GRIDLAYOUT_ROWS, 0));
        // setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        for (MathButton button : buttons) {
            // button.setMaximumSize(new Dimension(preferredSize, preferredSize));
            // button.setMinimumSize(new Dimension(preferredSize, preferredSize));
            // button.setPreferredSize(new Dimension(preferredSize, preferredSize));
            add(button);
            // button.setMargin(new Insets(0, 0, 0, 0));
            // System.out.println(button.getInsets());
        }

        setButtonAction();

        // System.out.println(cols);
        // System.out.println(getPreferredSize());
        // System.out.println(preferredSize);

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

    public void setMathButtonListener(MathButtonListener listener) {
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
