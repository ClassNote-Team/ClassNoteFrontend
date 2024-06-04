package org.example.MathEditPage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import org.example.base.BaseButton;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class MathButton extends BaseButton {

    protected TeXIcon icon;
    protected String latex;

    public MathButton() {
    }

    public void createButton(String latex, int size) {
        this.latex = latex;

        TeXFormula formula = new TeXFormula(latex);
        icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
        icon.setForeground(Color.WHITE);

        setMaximumSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    }

    public String getLatex() {
        return latex;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if(latex == null) {
            return;
        }

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        int x = (width - icon.getIconWidth()) / 2;
        int y = (height - icon.getIconHeight()) / 2;

        Area area = new Area(new Rectangle2D.Double(0, 0, width, height));

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2.setColor(color.getBackground());
        g2.fill(area);

        icon.paintIcon(this, g2, x, y);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color.getForeground());
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 0, 0));
        g2.dispose();
    }
}
