package MathEditPage;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

public class MathSymbolPanel extends JPanel{
    private ArrayList<MathButton> buttons;

    public MathSymbolPanel(){
        super(new FlowLayout(FlowLayout.LEFT));
        buttons = new ArrayList<MathButton>();
    }

    public void createMathSymbolPanel(int width, int height){
        setButton();

        int preferredSize = 0;
        for (MathButton button : buttons) {
            add(button);
            preferredSize = Math.max(preferredSize, button.getPreferredSize().width);
            preferredSize = Math.max(preferredSize, button.getPreferredSize().height);
        }

        for (MathButton button : buttons) {
            button.setPreferredSize(new Dimension(preferredSize, preferredSize));
            // button.setMargin(new Insets(0, 0, 0, 0));
            // System.out.println(button.getInsets());
        }
        System.out.println(getPreferredSize());
        setPreferredSize(new Dimension(width, height));
        validate();
        repaint();
    }

    private void setButton(){
        MathButton pulsButton = new MathButton();
        pulsButton.createButton("+", MathPageConstant.LATEX_FONT_SIZE);
        MathButton minusButton = new MathButton();
        minusButton.createButton("-", MathPageConstant.LATEX_FONT_SIZE);
        MathButton timesButton = new MathButton();
        timesButton.createButton("\\times", MathPageConstant.LATEX_FONT_SIZE);
        MathButton divButton = new MathButton();
        divButton.createButton("\\div", MathPageConstant.LATEX_FONT_SIZE);
        MathButton fracButton = new MathButton();
        fracButton.createButton("\\frac{x}{y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton powerButton = new MathButton();
        powerButton.createButton("x^{y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton greaterButton = new MathButton();
        greaterButton.createButton(">", MathPageConstant.LATEX_FONT_SIZE);
        MathButton lessButton = new MathButton();
        lessButton.createButton("<", MathPageConstant.LATEX_FONT_SIZE);
        MathButton equalButton = new MathButton();
        equalButton.createButton("=", MathPageConstant.LATEX_FONT_SIZE);
        MathButton greaterOrEqualButton = new MathButton();
        greaterOrEqualButton.createButton("\\geq", MathPageConstant.LATEX_FONT_SIZE);
        MathButton lessOrEqualButton = new MathButton();
        lessOrEqualButton.createButton("\\leq", MathPageConstant.LATEX_FONT_SIZE);
        MathButton sqrtButton = new MathButton();
        sqrtButton.createButton("\\sqrt{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton rootButton = new MathButton();
        rootButton.createButton("\\sqrt[y]{x}", MathPageConstant.LATEX_FONT_SIZE);

        buttons.add(pulsButton);
        buttons.add(minusButton);
        buttons.add(timesButton);
        buttons.add(divButton);
        buttons.add(fracButton);
        buttons.add(powerButton);
        buttons.add(greaterButton);
        buttons.add(lessButton);
        buttons.add(equalButton);
        buttons.add(greaterOrEqualButton);
        buttons.add(lessOrEqualButton);
        buttons.add(sqrtButton);
        buttons.add(rootButton);
    }
}
