package org.example.PaintPage;

import java.awt.*;

public abstract class PaintObject {
    private Color color;
    private int objectSize;
    public PaintObject(Color color, int objectSize) {
        this.color = color;
        this.objectSize = objectSize;
    }
    public PaintObject(){this.color = Color.BLACK;}
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public int getObjectSize() {
        return objectSize;
    }
    public void setObjectSize(int objectSize) {
        this.objectSize = objectSize;
    }
    public abstract void draw(Graphics2D g);
}
