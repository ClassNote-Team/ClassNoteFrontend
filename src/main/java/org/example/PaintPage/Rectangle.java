package org.example.PaintPage;

import java.awt.*;

public class Rectangle extends PaintObject{
    private int x1; // x coordinate of first endpoint
    private int y1; // y coordinate of first endpoint
    private int x2; // x coordinate of second endpoint
    private int y2; // y coordinate of second endpoint
    public Rectangle() {
        super();
        setX1(0); // set x coordinate of first endpoint
        setY1(0); // set y coordinate of first endpoint
        setX2(0); // set x coordinate of second endpoint
        setY2(0); // set y coordinate of second endpoint
    }
    public Rectangle(int x1, int y1, int x2, int y2, Color color) {
        super(color);
        setX1(x1); // set x coordinate of first endpoint
        setY1(y1); // set y coordinate of first endpoint
        setX2(x2); // set x coordinate of second endpoint
        setY2(y2); // set y coordinate of second endpoint
    }

    public Rectangle(int x1, int y1, int x2, int y2) {
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
    public int getUpperLeftX() {
        return Math.min(getX1(), getX2());
    }
    public int getUpperLeftY() {
        return Math.min(getY1(), getY2());
    }
    public int getWidth() {
        return Math.abs(getX2() - getX1());
    }
    public int getHeight() {
        return Math.abs(getY2() - getY1());
    }
    public void draw(Graphics g){
        g.setColor(getColor());
        g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }
}
