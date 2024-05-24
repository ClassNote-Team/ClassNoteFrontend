package org.example.MathEditPage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class PanelSwitchButton extends MathButton {

    @Override
    protected void paintComponent(Graphics g) {

        if(latex == null) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        int x = (getWidth() - icon.getIconWidth()) / 2;
        int y = (getHeight()- icon.getIconHeight()) / 2;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2.setColor(color.getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        icon.paintIcon(this, g2, x, y);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint border
    }
}
