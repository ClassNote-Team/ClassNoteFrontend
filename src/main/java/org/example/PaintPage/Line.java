package org.example.PaintPage;

import java.awt.*;

public class Line extends PaintObject{
    private int x1; // x coordinate of first endpoint
    private int y1; // y coordinate of first endpoint
    private int x2; // x coordinate of second endpoint
    private int y2; // y coordinate of second endpoint
    public Line(int x1, int y1, int x2, int y2, Color color, int objectSize) {
        super(color, objectSize);
        setX1(x1); // set x coordinate of first endpoint
        setY1(y1); // set y coordinate of first endpoint
        setX2(x2); // set x coordinate of second endpoint
        setY2(y2); // set y coordinate of second endpoint
    }
    public void setX1(int x1) {
        this.x1 = (Math.max(x1, 0));
    }
    public int getX1() {
        return x1;
    }
    public void setX2(int x2) {
        this.x2 = (Math.max(x2, 0));
    }
    public int getX2() {
        return x2;
    }
    public void setY1(int y1) {
        this.y1 = (Math.max(y1, 0));
    }
    public int getY1() {
        return y1;
    }
    public void setY2(int y2) {
        this.y2 = (Math.max(y2, 0));
    }
    public int getY2() {
        return y2;
    }
    public void draw(Graphics2D g){
        g.setColor(getColor());
        float thickness = getObjectSize();
        g.setStroke(new BasicStroke(thickness));
        g.drawLine(getX1(), getY1(), getX2(), getY2());
    }
}
