package MathEditPage;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class MathButton extends JButton{

    private TeXIcon icon;

    public void createButton(String latex, int size) {
        TeXFormula formula = new TeXFormula(latex);
        icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);

        setMaximumSize(new Dimension(icon.getIconWidth(), 23));
        setPreferredSize(new Dimension(icon.getIconWidth(), 23));
        validate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        icon.paintIcon(this, g2, 0, 0);
    }
}
