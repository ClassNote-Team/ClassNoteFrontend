package org.example.MathEditPage.Manager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.SecureRandom;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import org.example.MathEditPage.MathPageConstant;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class LaTeXManager {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public void render(String latex){
        TeXFormula formula = new TeXFormula(latex);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, MathPageConstant.LATEX_FONT_SIZE);
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2.setColor(new Color(255, 255, 255, 0));
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());

        JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 0));
        icon.paintIcon(label, g2, 0, 0);

        g2.dispose();

        try {
            ImageIO.write(image, "png", new File(MathPageConstant.IMAGE_PATH + generateRandomString(10) + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadImage(){

    }
}
