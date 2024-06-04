package org.example.base;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class BaseButton extends JButton {
    private int round = 10;
    private ButtonStyle style = ButtonStyle.SECONDARY;
    protected ButtonColor color = new ButtonColor(ButtonStyle.SECONDARY);

    public BaseButton(){
        init();
    }

    public BaseButton(String text){
        super(text);
        init();
    }

    private void init(){
        setContentAreaFilled(false);
        setForeground(color.foreground);
        // setFont(getFont().deriveFont(12f));
        setOpaque(false);  // 设置不透明，确保可以绘制圆角
        setFocusPainted(false);
        setBorderPainted(false);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                color.setBackground(style.backgroundPress);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                color.setBackground(style.background);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                color.setBackground(style.backgroundHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                color.setBackground(style.background);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, round, round));

        g2.setColor(color.background);
        g2.fill(area);

        g2.dispose();

        super.paintComponent(g);
    }

    public enum ButtonStyle {
        PRIMARY(new Color(0, 172, 126), new Color(238, 238, 238), new Color(2, 111, 82), new Color(4, 205, 151)),
        SECONDARY(new Color(103, 109, 119), new Color(230, 230, 230), new Color(81, 92, 108), new Color(70, 80, 90)),
        DESTRUCTIVE(new Color(255, 138, 48), new Color(238, 238, 238), new Color(198, 86, 0), new Color(255, 161, 90));

        private Color background;
        private Color foreground;
        private Color backgroundHover;
        private Color backgroundPress;

        private ButtonStyle(Color background, Color foreground, Color backgroundHover, Color backgroundPress) {
            this.background = background;
            this.foreground = foreground;
            this.backgroundHover = backgroundHover;
            this.backgroundPress = backgroundPress;
        }

    }

    protected class ButtonColor {

        private Color background;
        private Color foreground;
        private Color backgroundHover;
        private Color backgroundPress;

        public Color getBackground() {
            return background;
        }

        public void setBackground(Color background) {
            this.background = background;
        }

        public Color getForeground() {
            return foreground;
        }

        public void setForeground(Color foreground) {
            this.foreground = foreground;
        }

        public Color getBackgroundHover() {
            return backgroundHover;
        }

        public void setBackgroundHover(Color backgroundHover) {
            this.backgroundHover = backgroundHover;
        }

        public Color getBackgroundPress() {
            return backgroundPress;
        }

        public void setBackgroundPress(Color backgroundPress) {
            this.backgroundPress = backgroundPress;
        }

        public ButtonColor(ButtonStyle style) {
            changeStyle(style);
        }

        public ButtonColor() {
        }

        private void changeStyle(ButtonStyle style) {
            this.background = style.background;
            this.foreground = style.foreground;
            this.backgroundHover = style.backgroundHover;
            this.backgroundPress = style.backgroundPress;
        }
    }
}
