package org.example.PaintPage;

import java.awt.Color;
import java.awt.Graphics;

public abstract class PaintObject {
    private Color color;
    public PaintObject(Color color) {
        this.color = color;
    }
    public PaintObject(){this.color = Color.BLACK;}
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public abstract void draw(Graphics g);
}
