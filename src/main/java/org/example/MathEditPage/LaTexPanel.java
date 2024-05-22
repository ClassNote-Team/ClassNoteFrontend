package org.example.MathEditPage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class LaTexPanel extends JPanel{

    private TeXIcon icon;
    public void setText(String latex) {
        try {
            // 创建一个TeXFormula
            TeXFormula formula = new TeXFormula(latex);
            // 将TeXFormula转换为一个TeXIcon，设置大小和样式
            this.icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);

            // 设置组件的首选大小，足以显示 LaTeX
            setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (icon == null) {
            return;
        }

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // 绘制 LaTeX 图标
        icon.paintIcon(this, g2, 0, 0);
    }
}