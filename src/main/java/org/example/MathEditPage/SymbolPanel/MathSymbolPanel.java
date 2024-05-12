package org.example.MathEditPage.SymbolPanel;

import org.example.MathEditPage.MathButton;
import org.example.MathEditPage.MathPageConstant;

public class MathSymbolPanel extends SymbolPanel {

    public MathSymbolPanel() {
        super();
    }

    @Override
    protected void setButton(){
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
        MathButton absButton = new MathButton();
        absButton.createButton("|x|", MathPageConstant.LATEX_FONT_SIZE);
        MathButton floorButton = new MathButton();
        floorButton.createButton("\\lfloor x \\rfloor", MathPageConstant.LATEX_FONT_SIZE);
        MathButton ceilButton = new MathButton();
        ceilButton.createButton("\\lceil x \\rceil", MathPageConstant.LATEX_FONT_SIZE);
        MathButton combButton = new MathButton();
        combButton.createButton("\\binom{n}{k}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton modButton = new MathButton();
        modButton.createButton("mod", MathPageConstant.LATEX_FONT_SIZE);
        MathButton equivButton = new MathButton();
        equivButton.createButton("\\equiv", MathPageConstant.LATEX_FONT_SIZE);



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
        buttons.add(absButton);
        buttons.add(floorButton);
        buttons.add(ceilButton);
        buttons.add(combButton);
        buttons.add(modButton);
        buttons.add(equivButton);
    }
}
