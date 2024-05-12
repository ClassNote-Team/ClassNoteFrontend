package MathEditPage.SymbolPanel;

import MathEditPage.MathButton;
import MathEditPage.MathPageConstant;

public class CalculusSymbolPanel extends SymbolPanel {

    public CalculusSymbolPanel() {
        super();
    }

    @Override
    protected void setButton() {
        MathButton limitButton = new MathButton();
        limitButton.createButton("\\lim_{x\\to y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton sumButton = new MathButton();
        sumButton.createButton("\\sum_{i=x}^{y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton productButton = new MathButton();
        productButton.createButton("\\prod_{i=x}^{y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton differentialButton = new MathButton();
        differentialButton.createButton("\\frac{d}{dx}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton integralButton = new MathButton();
        integralButton.createButton("\\int_{x}^{y}dt", MathPageConstant.LATEX_FONT_SIZE);
        MathButton logButton = new MathButton();
        logButton.createButton("\\log_{x}{y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton lnButton = new MathButton();
        lnButton.createButton("\\ln_{x}{y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton expButton = new MathButton();
        expButton.createButton("\\exp^{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton sinButton = new MathButton();
        sinButton.createButton("\\sin{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton cosButton = new MathButton();
        cosButton.createButton("\\cos{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton tanButton = new MathButton();
        tanButton.createButton("\\tan{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton cotButton = new MathButton();
        cotButton.createButton("\\cot{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton secButton = new MathButton();
        secButton.createButton("\\sec{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton cscButton = new MathButton();
        cscButton.createButton("\\csc{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton sinhButton = new MathButton();
        sinhButton.createButton("\\sinh{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton coshButton = new MathButton();
        coshButton.createButton("\\cosh{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton tanhButton = new MathButton();
        tanhButton.createButton("\\tanh{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton cothButton = new MathButton();
        cothButton.createButton("\\coth{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton sechButton = new MathButton();
        sechButton.createButton("\\sech{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton cschButton = new MathButton();
        cschButton.createButton("\\csch{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton arcsinButton = new MathButton();
        arcsinButton.createButton("\\arcsin{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton arccosButton = new MathButton();
        arccosButton.createButton("\\arccos{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton arctanButton = new MathButton();
        arctanButton.createButton("\\arctan{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton arccotButton = new MathButton();
        arccotButton.createButton("\\arccot{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton arcsecButton = new MathButton();
        arcsecButton.createButton("\\arcsec{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton arccscButton = new MathButton();
        arccscButton.createButton("\\arccsc{x}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton bigcupButton = new MathButton();
        bigcupButton.createButton("\\bigcup_{i=x}^{y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton bigcapButton = new MathButton();
        bigcapButton.createButton("\\bigcap_{i=x}^{y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton bigveeButton = new MathButton();
        bigveeButton.createButton("\\bigvee_{i=x}^{y}", MathPageConstant.LATEX_FONT_SIZE);
        MathButton bigwedgeButton = new MathButton();
        bigwedgeButton.createButton("\\bigwedge_{i=x}^{y}", MathPageConstant.LATEX_FONT_SIZE);

        buttons.add(limitButton);
        buttons.add(sumButton);
        buttons.add(productButton);
        buttons.add(differentialButton);
        buttons.add(integralButton);
        buttons.add(logButton);
        buttons.add(lnButton);
        buttons.add(expButton);
        buttons.add(sinButton);
        buttons.add(cosButton);
        buttons.add(tanButton);
        buttons.add(cotButton);
        buttons.add(secButton);
        buttons.add(cscButton);
        buttons.add(sinhButton);
        buttons.add(coshButton);
        buttons.add(tanhButton);
        buttons.add(cothButton);
        buttons.add(sechButton);
        buttons.add(cschButton);
        buttons.add(arcsinButton);
        buttons.add(arccosButton);
        buttons.add(arctanButton);
        buttons.add(arccotButton);
        buttons.add(arcsecButton);
        buttons.add(arccscButton);
        buttons.add(bigcupButton);
        buttons.add(bigcapButton);
        buttons.add(bigveeButton);
        buttons.add(bigwedgeButton);
    }
}
