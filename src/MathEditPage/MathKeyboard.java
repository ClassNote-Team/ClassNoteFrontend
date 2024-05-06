package MathEditPage;

import javax.swing.JPanel;

public class MathKeyboard extends JPanel{

    private MathToolBar toolBar;

    public void createKeyboard(int width) {
        toolBar = new MathToolBar();
        toolBar.createToolBar(width);
        add(toolBar);
    }
}
