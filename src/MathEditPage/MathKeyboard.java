package MathEditPage;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MathKeyboard extends JPanel implements MathButtonListener {

    private MathToolBar toolBar;
    private MathSymbolPanel mathSymbolPanel;

    public void createKeyboard(int width, int height) {
        toolBar = new MathToolBar();
        toolBar.createToolBar(width);
        toolBar.setMathButtonListener(this);
        add(toolBar);

        mathSymbolPanel = new MathSymbolPanel();
        mathSymbolPanel.createMathSymbolPanel(width, height - toolBar.getHeight());
        add(mathSymbolPanel);
    }

    @Override
    public void onMathButtonPressed(String command) {
        System.out.println(command);
    }
}
