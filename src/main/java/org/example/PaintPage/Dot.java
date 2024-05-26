package org.example.PaintPage;

import java.awt.*;

public class Dot extends PaintObject{
    private int x;
    private int y;
    public Dot(){
        super();

    }
    public Dot(int x, int y){
        super();
        setX(x);
        setY(y);
    }
    public Dot(int x, int y, Color c, int objectSize){
        super(c, objectSize);
        setX(x);
        setY(y);
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    public void draw(Graphics2D g){
        g.setColor(getColor());
        int objectSize = getObjectSize();
        g.fillOval(getX() - (objectSize/2), getY() - (objectSize/2), objectSize, objectSize);
    }
}
