package org.example.MathEditPage;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.example.MathEditPage.MathPageConstant.PAGE_MODE;
import org.example.MathEditPage.SymbolPanel.CalculusSymbolPanel;
import org.example.MathEditPage.SymbolPanel.MathSymbolPanel;

public class MathKeyboard extends JPanel implements MathButtonListener {

    private MathToolBar toolBar;
    private MathSymbolPanel mathSymbolPanel;
    private CalculusSymbolPanel calculusSymbolPanel;
    private JTabbedPane tabbedPane;
    private JScrollPane scrollPane;

    public MathKeyboard() {
        toolBar = new MathToolBar();
        mathSymbolPanel = new MathSymbolPanel();
        calculusSymbolPanel = new CalculusSymbolPanel();
        tabbedPane = new JTabbedPane();
        scrollPane = new JScrollPane();
    }

    public void createKeyboard(int width, int height) {
        setLayout(new BorderLayout());
        toolBar.createToolBar(width);
        toolBar.setMathButtonListener(this);
        add(toolBar, BorderLayout.NORTH);


        mathSymbolPanel.createSymbolPanel(width, height - toolBar.getHeight());
        calculusSymbolPanel.createSymbolPanel(width, height - toolBar.getHeight());

        scrollPane.setViewportView(mathSymbolPanel);

        add(scrollPane, BorderLayout.CENTER);

        System.out.println("MathKeyboard: " + getPreferredSize());
        revalidate();
        repaint();
    }

    @Override
    public void onMathButtonPressed(PAGE_MODE mode) {

        if (mode == PAGE_MODE.MATH) {
            scrollPane.setViewportView(mathSymbolPanel);
        } else if (mode == PAGE_MODE.GREEK) {
        } else if (mode == PAGE_MODE.CALCULUS) {
            scrollPane.setViewportView(calculusSymbolPanel);
        }
        revalidate();
        repaint();
    }
}
