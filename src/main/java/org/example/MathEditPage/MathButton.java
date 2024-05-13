package org.example.MathEditPage;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class MathButton extends JButton{

    private TeXIcon icon;
    private String latex;

    public void createButton(String latex, int size) {
        this.latex = latex;

        TeXFormula formula = new TeXFormula(latex);
        icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);

        setMaximumSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    }

    public String getLatex() {
        return latex;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int x = (getWidth() - icon.getIconWidth()) / 2;
        int y = (getHeight()- icon.getIconHeight()) / 2;
        icon.paintIcon(this, g2, x, y);
    }
}
