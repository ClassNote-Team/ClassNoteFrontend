package org.example.PaintPage;

import javax.swing.*;

import org.example.PaintPage.PaintConstant.PaintObjectType;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private ArrayList<PaintObject> paintObjects = new ArrayList<PaintObject>();
    private int paintObjectCount;
    private PaintObject currentPaintObject;
    private TopBar topBar;
    public DrawPanel(TopBar topBar) {
        setBackground(Color.WHITE);
        this.topBar = topBar;
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void clearDrawing() {
        paintObjects.clear();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(PaintObject paintObject : paintObjects) {
            paintObject.draw(g);
        }
        if(currentPaintObject != null) {
            currentPaintObject.draw(g);
        }
    }
    private class MouseHandler extends MouseAdapter {
        // creates and sets the initial position for the new shape
        public void mousePressed(MouseEvent e) {
            if(topBar.getCurrentType() == PaintObjectType.RECTANGLE) {
                currentPaintObject = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), topBar.getCurrentColor());
            } else if(topBar.getCurrentType() == PaintObjectType.DOT) {
                paintObjects.add(new Dot(e.getX(), e.getY(), topBar.getCurrentColor()));
                repaint();
            }
        } // end method mousePressed

        // fixes the current shape onto the panel
        public void mouseReleased(MouseEvent e) {
            if(topBar.getCurrentType() == PaintObjectType.RECTANGLE) {
                ((Rectangle) currentPaintObject).setX2(e.getX());
                ((Rectangle) currentPaintObject).setY2(e.getY());
                paintObjects.add(currentPaintObject);
            }
            repaint();
        } // end method mouseReleased


        // update the shape to the current mouse position while dragging
        public void mouseDragged(MouseEvent e) {
            // TODO
            if(topBar.getCurrentType() == PaintObjectType.RECTANGLE) {
                ((Rectangle) currentPaintObject).setX2(e.getX());
                ((Rectangle) currentPaintObject).setY2(e.getY());
            }
            else if(topBar.getCurrentType() == PaintObjectType.DOT) {
                paintObjects.add(new Dot(e.getX(), e.getY(), topBar.getCurrentColor()));
            }
            repaint();
        } // end method mouseDragged

    }
}
