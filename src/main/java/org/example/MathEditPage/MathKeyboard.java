package org.example.MathEditPage;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.example.MathEditPage.MathPageConstant.PAGE_MODE;
import org.example.MathEditPage.SymbolPanel.CalculusSymbolPanel;
import org.example.MathEditPage.SymbolPanel.GreekSymbolPanel;
import org.example.MathEditPage.SymbolPanel.MathSymbolPanel;

public class MathKeyboard extends JPanel implements PageSwitchListener{

    private MathToolBar toolBar;
    private MathSymbolPanel mathSymbolPanel;
    private CalculusSymbolPanel calculusSymbolPanel;
    private GreekSymbolPanel greekSymbolPanel;
    private JScrollPane scrollPane;

    public MathKeyboard() {
        toolBar = new MathToolBar();
        mathSymbolPanel = new MathSymbolPanel();
        calculusSymbolPanel = new CalculusSymbolPanel();
        greekSymbolPanel = new GreekSymbolPanel();
        scrollPane = new JScrollPane();
    }

    public void createKeyboard(int width, int height) {
        setLayout(new BorderLayout());
        toolBar.createToolBar(width);
        toolBar.setMathButtonListener(this);
        add(toolBar, BorderLayout.NORTH);


        mathSymbolPanel.createSymbolPanel(width, height - toolBar.getHeight());
        calculusSymbolPanel.createSymbolPanel(width, height - toolBar.getHeight());
        greekSymbolPanel.createSymbolPanel(width, height - toolBar.getHeight());

        scrollPane.setViewportView(mathSymbolPanel);

        add(scrollPane, BorderLayout.CENTER);
        int cols = width / mathSymbolPanel.getButtonPreferredSize();
        int rows = mathSymbolPanel.getButtonCount() / cols;
        if (rows * cols < mathSymbolPanel.getButtonCount()) {
            rows++;
        }
        // System.out.println("MathKeyboard: " + getPreferredSize());
        // setPreferredSize(new Dimension(width, 3 * mathSymbolPanel.getButtonPreferredSize()));

        System.out.println("MathKeyboard: " + getPreferredSize());
        revalidate();
        repaint();
    }

    @Override
    public void onPageSwitch(PAGE_MODE mode) {

        if (mode == PAGE_MODE.MATH) {
            scrollPane.setViewportView(mathSymbolPanel);
        } else if (mode == PAGE_MODE.GREEK) {
            scrollPane.setViewportView(greekSymbolPanel);
        } else if (mode == PAGE_MODE.CALCULUS) {
            scrollPane.setViewportView(calculusSymbolPanel);
        }
        revalidate();
        repaint();
    }

    public void setMathButtonListener(MathButtonListener listener) {
        mathSymbolPanel.setMathButtonListener(listener);
        calculusSymbolPanel.setMathButtonListener(listener);
        greekSymbolPanel.setMathButtonListener(listener);
    }
}
